package com.payday.stocktradesystem.service.confirmationToken;

import com.payday.stocktradesystem.domain.confirmationtoken.ConfirmationToken;
import com.payday.stocktradesystem.repository.confirmationtoken.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenService {

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    public void save(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    public ConfirmationToken findByConfirmationToken(String confirmationToken) {
        return confirmationTokenRepository.findByConfirmationToken(confirmationToken);
    }
}
