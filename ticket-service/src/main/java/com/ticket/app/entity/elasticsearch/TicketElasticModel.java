package com.ticket.app.entity.elasticsearch;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@EqualsAndHashCode(of = {"ticket_id"})
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "ticket")
public class TicketElasticModel implements Serializable {
    @Id
    private String ticket_id;

    private String description;

    private String notes;

    private String assignee;

    private Date ticketDate;

    private String priorityType;

    private String ticketStatus;
}
