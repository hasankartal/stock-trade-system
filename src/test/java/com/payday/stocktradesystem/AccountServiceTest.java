package com.payday.stocktradesystem;

import com.payday.stocktradesystem.domain.account.Account;
import com.payday.stocktradesystem.domain.user.User;
import com.payday.stocktradesystem.model.account.AccountDto;
import com.payday.stocktradesystem.repository.account.AccountRepository;
import com.payday.stocktradesystem.service.account.AccountService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @InjectMocks
    AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    public void findByUser() {
        User user = new User();
        user.setUserId(1);

        Account account = new Account();
        account.setCash(new BigDecimal(125));
        account.setUser(user);
        Mockito.when(accountService.findByUser(user)).thenReturn(account);
        Account accountTest = accountService.findByUser(user);

        Assertions.assertEquals(accountTest, account);
    }

    @Test
    public void loadCash() {
        User user = new User();
        user.setUserId(1);

        AccountDto accountDto = new AccountDto();
        accountDto.setCash(new BigDecimal(125));
        accountDto.setUserId(1);

        //Mockito.when(accountService.loadCash(accountDto,user)).thenReturn(account);
        //ResponseEntity<Object> accountTest = accountService.loadCash(accountDto,user);

        //Assertions.assertEquals(accountTest, ResponseEntity.ok());

    }
}
