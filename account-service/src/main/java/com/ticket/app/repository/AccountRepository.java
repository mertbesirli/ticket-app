package com.ticket.app.repository;

import com.ticket.app.entity.Account;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface AccountRepository extends CassandraRepository<Account, String> {
}
