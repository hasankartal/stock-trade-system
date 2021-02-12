package com.payday.stocktradesystem;

import com.payday.stocktradesystem.domain.confirmationtoken.ConfirmationToken;
import com.payday.stocktradesystem.domain.user.User;
import com.payday.stocktradesystem.repository.confirmationtoken.ConfirmationTokenRepository;
import com.payday.stocktradesystem.service.confirmationToken.ConfirmationTokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ConfirmationTokenServiceTest {

    @InjectMocks
    ConfirmationTokenService confirmationTokenService;

    @Mock
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Test
    public void findByConfirmationToken() {
        User user = new User();
        user.setUserId(1);

        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setConfirmationToken("419f7735-0699-4544-9a23-f4f0ee650f01");
        confirmationToken.setUser(user);

        Mockito.when(confirmationTokenService.findByConfirmationToken("419f7735-0699-4544-9a23-f4f0ee650f01")).thenReturn(confirmationToken);
        ConfirmationToken confirmationTokenTest = confirmationTokenService.findByConfirmationToken("419f7735-0699-4544-9a23-f4f0ee650f01");

        Assertions.assertEquals(confirmationTokenTest, confirmationToken);
    }
}
