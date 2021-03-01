package com.payday.stocktradesystem.model.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDtoTest {
    private static final String EXPECTED_USER_NAME = "Hasan";
    private static final String EXPECTED_EMAIL = "hasan.kartal@obss.com.tr";
    private static final String EXPECTED_PASSWORD = "testPassword";
    private UserDto userDto;

    @Test
    void getEmail() {
        userDto = new UserDto();
        userDto.setEmail("hasan.kartal@obss.com.tr");
        Assertions.assertEquals(EXPECTED_EMAIL, userDto.getEmail());
    }

    @Test
    void getPassword() {
        userDto = new UserDto();
        userDto.setPassword("testPassword");
        Assertions.assertEquals(EXPECTED_PASSWORD, userDto.getPassword());
    }

    @Test
    void getUserName() {
        userDto = new UserDto();
        userDto.setUserName("Hasan");
        Assertions.assertEquals(EXPECTED_USER_NAME, userDto.getUserName());
    }
}