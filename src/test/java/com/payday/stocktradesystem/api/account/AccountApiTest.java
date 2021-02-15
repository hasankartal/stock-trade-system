package com.payday.stocktradesystem.api.account;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payday.stocktradesystem.api.user.UserApi;
import com.payday.stocktradesystem.domain.user.User;
import com.payday.stocktradesystem.exception.DataIntegrityViolationDbException;
import com.payday.stocktradesystem.model.account.AccountDto;
import com.payday.stocktradesystem.model.orderstock.OrderstockDto;
import com.payday.stocktradesystem.service.account.impl.AccountServiceImpl;
import com.payday.stocktradesystem.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AccountApi.class)
class AccountApiTest {
    private static final long EXPECTED_USER_ID = 1;
    private static final BigDecimal EXPECTED_PRICE = new BigDecimal("100");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    AccountServiceImpl accountServiceImpl;

    @MockBean
    UserService userService;

    @Test
    void loadCash() {
        AccountDto accountDto = new AccountDto();
        accountDto.setUserId(EXPECTED_USER_ID);
        accountDto.setCash(EXPECTED_PRICE);

        User existingUser = new User();
        existingUser.setUserId(EXPECTED_USER_ID);
        existingUser.setEnabled(true);

        Mockito
                .when(userService.findByUserId(accountDto.getUserId()))
                .thenReturn(existingUser);
        try {
            this.mockMvc.perform(post("/loadCash")
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(accountDto)))
                    .andDo(print());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}