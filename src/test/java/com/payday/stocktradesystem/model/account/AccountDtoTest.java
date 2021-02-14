package com.payday.stocktradesystem.model.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class AccountDtoTest {
    private static final BigDecimal EXPECTED_CASH = new BigDecimal(120);
    private static final long EXPECTED_USER_ID = 30;
    private AccountDto accountDto;

    @Test
    void getCash() {
        accountDto = new AccountDto();
        accountDto.setCash(EXPECTED_CASH);
        Assertions.assertEquals(EXPECTED_CASH, accountDto.getCash());
    }

    @Test
    void getUserId() {
        accountDto = new AccountDto();
        accountDto.setUserId(EXPECTED_USER_ID);
        Assertions.assertEquals(EXPECTED_USER_ID, accountDto.getUserId());
    }
}