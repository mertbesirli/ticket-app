package com.ticket.app.client.contract;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDto {
    private String acc_id ;
    private String username;
    private String name;
    private String surname;
    private String email;
    private Date birthDate;
    public String getNameSurname() {
        return this.name + " " + this.surname;
    }
}