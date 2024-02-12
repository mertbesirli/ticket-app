package com.ticket.app.service;

import com.ticket.app.entity.Ticket;
import com.ticket.app.messaging.TicketNotification;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@EnableBinding(Source.class)
@RequiredArgsConstructor
public class TicketNotificationService {

    private final Source source;

    public void sendToQueue(Ticket ticket) {
        TicketNotification ticketNotification =new TicketNotification();
        ticketNotification.setAccountId(ticket.getAssignee());
        ticketNotification.setTicketId(ticket.getTicket_id());
        ticketNotification.setTicketDescription(ticket.getDescription());

        source.output().send(MessageBuilder.withPayload(ticketNotification).build());
    }
}
