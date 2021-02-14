package com.payday.stocktradesystem.service.account.impl;

import com.payday.stocktradesystem.domain.account.Account;
import com.payday.stocktradesystem.domain.user.User;
import com.payday.stocktradesystem.exception.DataIntegrityViolationDbException;
import com.payday.stocktradesystem.model.account.AccountDto;
import com.payday.stocktradesystem.repository.account.AccountRepository;
import com.payday.stocktradesystem.service.account.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    @Override
    public Account loadCash(AccountDto accountDto, User user) {
        Account account = new Account();
        account.setUser(user);

        Account existingAccount = accountRepository.findByUser(user);
        if (existingAccount != null) {
            account.setAccountId(existingAccount.getAccountId());
            account.setCash(accountDto.getCash().add(existingAccount.getCash()));
        } else {
            account.setCash(accountDto.getCash());
        }

        return accountRepository.save(account);
    }

    @Override
    public Account withdrawCash(AccountDto accountDto, User user) {
        Account account = new Account();
        account.setUser(user);

        Account existingAccount = accountRepository.findByUser(user);
        if (existingAccount != null) {
            account.setAccountId(existingAccount.getAccountId());
            BigDecimal money = existingAccount.getCash().subtract(accountDto.getCash());
            if(money.compareTo(BigDecimal.ZERO) == -1) {
                throw new DataIntegrityViolationDbException("There is not enough money on account");
            }
            account.setCash(money);
        } else {
      //      throw new DataIntegrityViolationDbException("Couldn't find account.");
        }

        return  accountRepository.save(account);
    }

    @Override
    public Account findByUser(User user) {
        return accountRepository.findByUser(user);
    }
}
