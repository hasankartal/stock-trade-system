package com.payday.stocktradesystem.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import springfox.documentation.service.Contact;

import static org.junit.jupiter.api.Assertions.*;

class SwaggerConfigTest {

    @Test
    void api() {
    }

    @Test
    void contactTest() {
        Contact DEFAULT_CONTACT = new Contact("Hasan Kartal", "http://www.payday.com", "test@gmail.com");

        Assertions.assertEquals(DEFAULT_CONTACT.getName(), SwaggerConfig.DEFAULT_CONTACT.getName());
        Assertions.assertEquals(DEFAULT_CONTACT.getEmail(), SwaggerConfig.DEFAULT_CONTACT.getEmail());
        Assertions.assertEquals(DEFAULT_CONTACT.getUrl(), SwaggerConfig.DEFAULT_CONTACT.getUrl());
    }
}