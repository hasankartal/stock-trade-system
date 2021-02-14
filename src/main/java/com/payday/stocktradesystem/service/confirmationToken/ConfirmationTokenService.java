package com.payday.stocktradesystem.service.confirmationToken;

import com.payday.stocktradesystem.domain.confirmationtoken.ConfirmationToken;

public interface ConfirmationTokenService {
    ConfirmationToken save(ConfirmationToken confirmationToken);
    ConfirmationToken findByConfirmationToken(String confirmationToken);
}
