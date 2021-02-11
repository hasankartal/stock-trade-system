package com.payday.stocktradesystem.service.account;

import com.payday.stocktradesystem.domain.account.Account;
import com.payday.stocktradesystem.domain.user.User;
import com.payday.stocktradesystem.exception.DataIntegrityViolationDbException;
import com.payday.stocktradesystem.model.account.AccountDto;
import com.payday.stocktradesystem.repository.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public ResponseEntity<Object> loadCash(AccountDto accountDto, User user) {
        Account account = new Account();
        account.setUser(user);

        Account existingAccount = accountRepository.findByUser(user);
        if (existingAccount != null) {
            account.setAccountId(existingAccount.getAccountId());
            account.setCash(accountDto.getCash().add(existingAccount.getCash()));
        } else {
            account.setCash(accountDto.getCash());
        }
        accountRepository.save(account);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Object> withdrawCash(AccountDto accountDto, User user) {
        Account account = new Account();
        account.setUser(user);

        Account existingAccount = accountRepository.findByUser(user);
        if (existingAccount != null) {
            account.setAccountId(existingAccount.getAccountId());
            BigDecimal money = existingAccount.getCash().subtract(accountDto.getCash());
            if(money.compareTo(BigDecimal.ZERO) == 1) {
                throw new DataIntegrityViolationDbException("There is not enough money on account");
            }
            account.setCash(money);
        } else {
      //      throw new DataIntegrityViolationDbException("Couldn't find account.");
        }
        accountRepository.save(account);

        return ResponseEntity.ok().build();
    }
}
