package com.payday.stocktradesystem.service.email;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@ExtendWith(MockitoExtension.class)
class EmailSenderServiceTest {
    private static final String toMail = "hasankartal18@gmail.com";
    private static final String subject = "TEST";
    private static final String fromMail = "hasan.kartal@obss.com";
    private static final String text = "TEST Text";

    @InjectMocks
    EmailSenderService emailSenderService;

    @Mock
    JavaMailSender javaMailSender;

    @Test
    void sendEmail() {
        JavaMailSender javaMailSender = new JavaMailSenderImpl();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        emailSenderService.sendEmail(toMail,subject,fromMail,text);
    }

}