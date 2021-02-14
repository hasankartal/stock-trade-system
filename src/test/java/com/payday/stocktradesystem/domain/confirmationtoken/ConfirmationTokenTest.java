package com.payday.stocktradesystem.domain.confirmationtoken;

import com.payday.stocktradesystem.domain.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

class ConfirmationTokenTest {
    private static final long EXPECTED_TOKEN_ID = 30;
    private static final long EXPECTED_USER_ID = 1;
    private static final String EXPECTED_TOKEN = "419f7735-0699-4544-9a23-f4f0ee650f01";
    private ConfirmationToken confirmationToken;

    @Test
    void getTokenId() {
        confirmationToken = new ConfirmationToken();
        confirmationToken.setTokenId(EXPECTED_TOKEN_ID);
        Assertions.assertEquals(EXPECTED_TOKEN_ID, confirmationToken.getTokenId());
    }

    @Test
    void getConfirmationToken() {
        confirmationToken = new ConfirmationToken();
        confirmationToken.setConfirmationToken(EXPECTED_TOKEN);
        Assertions.assertEquals(EXPECTED_TOKEN, confirmationToken.getConfirmationToken());
    }

    @Test
    void getCreatedDate() {
        confirmationToken = new ConfirmationToken();
        Date createdDate = new Date();
        confirmationToken.setCreatedDate(createdDate);
        Assertions.assertEquals(createdDate, confirmationToken.getCreatedDate());
    }

    @Test
    void getUser() {
        confirmationToken = new ConfirmationToken();
        User user = new User();
        user.setUserId(EXPECTED_USER_ID);
        confirmationToken.setUser(user);

        Assertions.assertEquals(user, confirmationToken.getUser());
    }

    @Test
    public void setConfirmationTokenParameter() {
        User user = new User();
        user.setUserId(EXPECTED_USER_ID);

        ConfirmationToken confirmationTokenParameter = new ConfirmationToken(user);
        ConfirmationToken confirmationTokenWithoutParameter = new ConfirmationToken();
        Assertions.assertNotEquals(confirmationTokenWithoutParameter, confirmationTokenParameter);
    }
}