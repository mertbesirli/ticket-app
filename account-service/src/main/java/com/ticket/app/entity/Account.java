package com.ticket.app.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"acc_id"})
@ToString
@Table(value = "accounts")
public class Account implements Serializable {
    @PrimaryKey
    private String acc_id = UUID.randomUUID().toString();
    @Setter
    @Column(value = "uname")
    private String username;

    @Setter
    @Column(value = "name")
    private String name;

    @Setter
    @Column(value = "surname")
    private String surname;

    @Setter
    @Column(value = "email")
    private String email;

    @Setter
    @Column(value = "birth_date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @Setter
    @Column(value = "pwd")
    private String passwd;

    @Column(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @Column(value = "is_active")
    private Boolean active;

}
