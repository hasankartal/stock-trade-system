package com.payday.stocktradesystem.service.user.impl;

import com.payday.stocktradesystem.domain.confirmationtoken.ConfirmationToken;
import com.payday.stocktradesystem.domain.user.User;
import com.payday.stocktradesystem.exception.DataIntegrityViolationDbException;
import com.payday.stocktradesystem.model.user.UserDto;
import com.payday.stocktradesystem.repository.user.UserRepository;
import com.payday.stocktradesystem.service.confirmationToken.ConfirmationTokenService;
import com.payday.stocktradesystem.service.email.EmailSenderService;
import com.payday.stocktradesystem.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public User findByEmailIdIgnoreCase(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findByUserId(long userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public User findByEmailIdAndPassword(String email, String password) {
        return userRepository.findByEmailEqualsAndPasswordEquals(email, password);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public String register(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUserName(userDto.getUserName());
        user.setEnabled(false);

        try{
            userRepository.save(user);

            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenService.save(confirmationToken);

            return confirmationToken.getConfirmationToken();
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationDbException("Could not register in db");
        }
    }
}
