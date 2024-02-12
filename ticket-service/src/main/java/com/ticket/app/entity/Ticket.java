package com.ticket.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@EqualsAndHashCode(of = "{id}")
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticket")
public class Ticket{

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ticket_id")
    private String ticket_id;
    @Setter
    @Column(name = "description", length = 600)
    private String description;
    @Setter
    @Column(name = "notes", length = 1000)
    private String notes;
    @Setter
    @Column(name = "assignee", length = 50)
    private String assignee;
    @Setter
    @Column(name = "ticket_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date ticketDate;
    @Setter
    @Column(name = "priority_type")
    private PriorityType priorityType;
    @Setter
    @Column(name = "ticket_status")
    private TicketStatus ticketStatus;
}
