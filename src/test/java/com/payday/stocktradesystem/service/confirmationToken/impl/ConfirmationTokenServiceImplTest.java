package com.payday.stocktradesystem.service.confirmationToken.impl;

import com.payday.stocktradesystem.domain.confirmationtoken.ConfirmationToken;
import com.payday.stocktradesystem.domain.user.User;
import com.payday.stocktradesystem.repository.confirmationtoken.ConfirmationTokenRepository;
import com.payday.stocktradesystem.service.confirmationToken.impl.ConfirmationTokenServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConfirmationTokenServiceImplTest {
    public static final long EXPECTED_USER_ID = 1;
    public static final String EXPECTED_TOKEN = "419f7735-0699-4544-9a23-f4f0ee650f01";

    @InjectMocks
    ConfirmationTokenServiceImpl confirmationTokenServiceImpl;

    @Mock
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Test
    void save() {
        User user = new User();
        user.setUserId(EXPECTED_USER_ID);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);

        Mockito.when(confirmationTokenServiceImpl.save(confirmationToken)).thenReturn(confirmationToken);
        ConfirmationToken confirmationTokenTest = confirmationTokenServiceImpl.save(confirmationToken);

        Assertions.assertEquals(confirmationTokenTest.getUser(), confirmationToken.getUser());
    }

    @Test
    void findByConfirmationToken() {
        User user = new User();
        user.setUserId(EXPECTED_USER_ID);

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setConfirmationToken(EXPECTED_TOKEN);
        confirmationToken.setUser(user);

        Mockito.when(confirmationTokenServiceImpl.findByConfirmationToken(EXPECTED_TOKEN)).thenReturn(confirmationToken);
        ConfirmationToken confirmationTokenTest = confirmationTokenServiceImpl.findByConfirmationToken(EXPECTED_TOKEN);

        Assertions.assertEquals(confirmationTokenTest.getTokenId(), confirmationToken.getTokenId());
    }
}