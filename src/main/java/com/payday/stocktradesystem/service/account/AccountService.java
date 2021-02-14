package com.payday.stocktradesystem.service.account;

import com.payday.stocktradesystem.domain.account.Account;
import com.payday.stocktradesystem.domain.user.User;
import com.payday.stocktradesystem.model.account.AccountDto;

public interface AccountService {
    Account loadCash(AccountDto accountDto, User user);
    Account withdrawCash(AccountDto accountDto, User user);
    Account findByUser(User user);
}
