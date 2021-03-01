package com.payday.stocktradesystem.model.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserSignInDtoTest {
    private static final String EXPECTED_EMAIL = "hasan.kartal@obss.com.tr";
    private static final String EXPECTED_PASSWORD = "testPassword";
    private UserSignInDto userSignInDto;

    @Test
    void getEmail() {
        userSignInDto = new UserSignInDto();
        userSignInDto.setEmail("hasan.kartal@obss.com.tr");
        Assertions.assertEquals(EXPECTED_EMAIL, userSignInDto.getEmail());
    }

    @Test
    void getPassword() {
        userSignInDto = new UserSignInDto();
        userSignInDto.setPassword("testPassword");
        Assertions.assertEquals(EXPECTED_PASSWORD, userSignInDto.getPassword());
    }
}