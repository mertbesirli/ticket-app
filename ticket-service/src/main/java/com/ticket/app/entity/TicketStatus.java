package com.ticket.app.entity;

import lombok.Getter;

@Getter
public enum TicketStatus {
    OPEN("Open"),
    IN_PROGRESS("Progressing"),
    RESOLVED("Solved"),
    CLOSED("Closed");

    private String label;

    TicketStatus(String label){
        this.label = label;
    }


}
