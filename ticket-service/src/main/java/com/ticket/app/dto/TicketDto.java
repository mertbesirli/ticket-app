package com.ticket.app.dto;

import lombok.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    private String ticket_id;
    private String description;
    private String notes;
    private String assignee;
    private Date ticketDate;
    private String priorityType;
    private String ticketStatus;
}
