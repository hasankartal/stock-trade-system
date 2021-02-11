package com.payday.stocktradesystem.repository.account;

import com.payday.stocktradesystem.domain.account.Account;
import com.payday.stocktradesystem.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUser(User userId);
}
