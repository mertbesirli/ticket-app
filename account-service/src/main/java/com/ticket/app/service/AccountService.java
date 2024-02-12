package com.ticket.app.service;

import com.ticket.app.client.contract.AccountDto;
import com.ticket.app.entity.Account;
import com.ticket.app.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public AccountDto get(String id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        return modelMapper.map(account, AccountDto.class);
    }

    @Transactional
    public AccountDto save(AccountDto accountDto) {
        Account account = modelMapper.map(accountDto, Account.class);
        account = accountRepository.save(account);
        accountDto.setAcc_id(account.getAcc_id());
        return accountDto;
    }

    @Transactional
    public AccountDto update(String id, AccountDto accountDto) {
        Assert.isNull(id, "Id cannot be null");
        Optional<Account> account = accountRepository.findById(id);
        Account accountToUpdate = account.map(it -> {
            it.setBirthDate(accountDto.getBirthDate());
            it.setName(accountDto.getName());
            it.setSurname(accountDto.getSurname());
            return it;
        }).orElseThrow(IllegalArgumentException::new);
        return modelMapper.map(accountRepository.save(accountToUpdate), AccountDto.class);
    }

    @Transactional
    public void delete(String id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException());
        accountRepository.delete(account);
    }

    public List<AccountDto> getAll() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountDto> entityToDto = modelMapper.map(accounts, new TypeToken<List<AccountDto>>(){}.getType());
        return entityToDto;
    }

}
