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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSenderService emailSenderService;

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
    public ResponseEntity<Object> register(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUserName(userDto.getUserName());
        user.setEnabled(false);

        User savedUser;
        try{
            savedUser = userRepository.save(user);

            ConfirmationToken confirmationToken = new ConfirmationToken(user);
            confirmationTokenService.save(confirmationToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(userDto.getEmail());
            mailMessage.setSubject("Complete Registration!");
            mailMessage.setFrom("hasankartal18@gmail.com");
            mailMessage.setText("To confirm your account, please click here : "
                    +"http://localhost:8082/confirm-account?token="+confirmationToken.getConfirmationToken());

            emailSenderService.sendEmail(mailMessage);
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationDbException("Could not register in db");
        } catch (Exception ex) {
            //TODO Mail Exception
            throw new DataIntegrityViolationDbException("Could not register in db");
        }

        //TODO Location will be removed
        URI location =
                ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                        .buildAndExpand(savedUser.getUserId())
                        .toUri();

        return ResponseEntity.created(location).build();
    }
}
