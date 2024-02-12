package com.ticket.app.controller;

import com.ticket.app.client.contract.AccountDto;
import com.ticket.app.service.AccountService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/v1/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{id}")
    @CircuitBreaker(name = "account", fallbackMethod = "getfallbackMethod")
    @TimeLimiter(name = "account")
    public CompletableFuture<ResponseEntity<AccountDto>> get(@PathVariable("id") String id) {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(accountService.get(id)));
    }

    @PostMapping
    @CircuitBreaker(name = "account", fallbackMethod = "postfallbackMethod")
    @TimeLimiter(name = "account")
    public CompletableFuture<ResponseEntity<AccountDto>> save(@RequestBody AccountDto account) {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(accountService.save(account)));
    }

    @PutMapping
    public ResponseEntity<AccountDto> update(@PathVariable("id") String id, @RequestBody AccountDto account) {
        return ResponseEntity.ok(accountService.update(id, account));
    }

    @DeleteMapping
    public void delete(String id) {
        accountService.delete(id);
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAll() {
        return ResponseEntity.ok(accountService.getAll());
    }
    public CompletableFuture<String> getfallbackMethod(@PathVariable("id") String id, RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(() -> "Something went wrong, please try after time!");
    }

    public CompletableFuture<String> postfallbackMethod(@RequestBody AccountDto account, RuntimeException runtimeException){
        return CompletableFuture.supplyAsync(() -> "Something went wrong, please save account after time!");
    }
}
