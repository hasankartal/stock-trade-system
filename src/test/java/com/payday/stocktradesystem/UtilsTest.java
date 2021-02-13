package com.payday.stocktradesystem;

import com.payday.stocktradesystem.model.stock.StockPrice;
import com.payday.stocktradesystem.util.Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;

@ExtendWith(MockitoExtension.class)
public class UtilsTest {
    public static final String EXPECTED_BUY = "BUY";
    public static final String EXPECTED_SELL = "SELL";

    public static final String EXPECTED_TO = "hasan.kartal@obss.com.tr";
    public static final String EXPECTED_SUBJECT = "SELL Notification";
    public static final String EXPECTED_FROM = "paydataassignment@gmail.com";
    public static final String EXPECTED_TEXT = "Text";

    @Test
    public void setExpectedBuy() {
        Assertions.assertEquals(EXPECTED_BUY, Utils.BUY);
    }

    @Test
    public void setExpectedSell() {
        Assertions.assertEquals(EXPECTED_SELL, Utils.SELL);
    }

    /*
    @Test
    public void setSendEmail() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(EXPECTED_TO);
        mailMessage.setSubject(EXPECTED_SUBJECT);
        mailMessage.setFrom(EXPECTED_FROM);
        mailMessage.setText(EXPECTED_SUBJECT);

        Assertions.assertEquals(mailMessage, Utils.sendEmail(EXPECTED_TO, EXPECTED_SUBJECT, EXPECTED_FROM, EXPECTED_SUBJECT));
    }
    */
}
