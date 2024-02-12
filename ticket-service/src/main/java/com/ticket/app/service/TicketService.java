package com.ticket.app.service;

import com.ticket.app.client.AccountServiceClient;
import com.ticket.app.client.contract.AccountDto;
import com.ticket.app.dto.TicketDto;
import com.ticket.app.entity.PriorityType;
import com.ticket.app.entity.Ticket;
import com.ticket.app.entity.TicketStatus;
import com.ticket.app.entity.elasticsearch.TicketElasticModel;
import com.ticket.app.repository.TicketRepository;
import com.ticket.app.repository.elasticsearch.TicketElasticRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final TicketElasticRepository ticketElasticRepository;
    private final TicketNotificationService ticketNotificationService;
    private final AccountServiceClient accountServiceClient;
    private final ModelMapper modelMapper;

    // It is not suitable for SOLID, change later..
    @Transactional
    public TicketDto save(TicketDto ticketDto){
        if(ticketDto.getDescription() == null)
            throw new IllegalArgumentException("Description cannot be empty");
        // Ticket entity
        Ticket ticket = new Ticket();
        ResponseEntity<AccountDto> accountDtoResponseEntity = accountServiceClient.get(ticketDto.getAssignee());

        ticket.setDescription(ticketDto.getDescription());
        ticket.setNotes(ticketDto.getNotes());
        ticket.setTicketDate(ticketDto.getTicketDate());
        ticket.setTicketStatus(TicketStatus.valueOf(ticketDto.getTicketStatus()));
        ticket.setPriorityType(PriorityType.valueOf(ticketDto.getPriorityType()));
        ticket.setAssignee(accountDtoResponseEntity.getBody().getAcc_id());
        // Save db to mysql
        ticket = ticketRepository.save(ticket);

        // Ticket model for elastic search
        TicketElasticModel ticketElasticModel = TicketElasticModel.builder()
                .description(ticket.getDescription())
                .notes(ticket.getNotes())
                .ticketDate(ticket.getTicketDate())
                .ticket_id(ticket.getTicket_id())
                .assignee(accountDtoResponseEntity.getBody().getNameSurname())
                .ticketStatus(ticket.getTicketStatus().getLabel())
                .priorityType(ticket.getPriorityType().getLabel())
                .build();
        // Save db to elastic-search
        ticketElasticRepository.save(ticketElasticModel);

        ticketDto.setTicket_id(ticket.getTicket_id());
        // send message to queue
        ticketNotificationService.sendToQueue(ticket);

        return ticketDto;
    }

    public TicketDto update(String id, TicketDto ticketDto){
        return null;
    }

    public TicketDto getById(String ticketId){
        return null;
    }

    public List<TicketDto> getAll(){
        List<Ticket> tickets = ticketRepository.findAll();
        List<TicketDto> entityToDto = modelMapper.map(tickets, new TypeToken<List<TicketDto>>(){}.getType());
        return entityToDto;
    }

}
