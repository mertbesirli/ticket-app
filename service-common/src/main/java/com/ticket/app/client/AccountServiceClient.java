package com.ticket.app.client;

import com.ticket.app.client.contract.AccountDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "account-service")
public interface AccountServiceClient {
    @RequestMapping("/v1/account/{id}")
    ResponseEntity<AccountDto> get(@PathVariable("id") String id);
}
