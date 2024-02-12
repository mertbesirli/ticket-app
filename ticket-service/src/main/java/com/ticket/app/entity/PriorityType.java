package com.ticket.app.entity;

import lombok.Getter;

@Getter
public enum PriorityType {
    URGENT("Emergency"),
    LOW("Not Important"),
    HIGH("High Emergency");

    private String label;

    PriorityType(String label){
        this.label = label;
    }
}
