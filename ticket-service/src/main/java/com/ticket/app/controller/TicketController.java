package com.ticket.app.controller;

import com.ticket.app.dto.TicketDto;
import com.ticket.app.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/ticket")
public class TicketController {

    private final TicketService ticketService;
    @GetMapping("/{id}")
    public ResponseEntity<TicketDto> getById(@PathVariable("id") String id){
        return ResponseEntity.ok(ticketService.getById(id));
    }

    @PostMapping
    public ResponseEntity<TicketDto> createTicket(@RequestBody TicketDto ticketDto) {
        return ResponseEntity.ok(ticketService.save(ticketDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable String id,
                                                  @RequestBody TicketDto ticketDto) {
        return ResponseEntity.ok(ticketService.update(id, ticketDto));
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getAll() {
        return ResponseEntity.ok(ticketService.getAll());
    }
}
