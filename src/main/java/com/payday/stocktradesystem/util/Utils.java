package com.payday.stocktradesystem.util;

import org.springframework.mail.SimpleMailMessage;

public class Utils {
    public static String BUY = "BUY";
    public static String SELL = "SELL";

    public static SimpleMailMessage sendEmail(String email, String subject, String from, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject(subject);
        mailMessage.setFrom(from);
        mailMessage.setText(text);
        return simpleMailMessage;
    }
}
