package com.pragma.userservice.domain.api;

import com.pragma.userservice.infrastructure.driven_adapters.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IUserValidatorTest {
    IUserValidator userValidator;

    @BeforeEach
    void setUp(){
        userValidator = new UserValidator();
    }
    @Test
    void goodEmails() {
        List<String> emails = List.of("javaTpoint@domain.co.in", "12453@domain.com", "javaTpoint.name@domain.com",
                "1avaTpoint#@domain.co.in", "good@email");
        emails.forEach( (email) -> assertTrue(userValidator.emailChecker(email)));
    }

    @Test
    void badEmails() {
        List<String> emails = List.of("@yahoo.com", "hola", "bad@");
        emails.forEach( (email) -> assertFalse(userValidator.emailChecker(email)));
    }


    @Test
    void goodPhones() {
        List<String> phones = List.of("31423", "+2132424", "2389723");
        phones.forEach(
                (phone) -> assertTrue( userValidator.phoneChecker(phone))
        );
    }

    @Test
    public void badPhones() {
        List<String> phones = List.of("314233a", "*2132424", "2389723231242141231");
        phones.forEach(
                (phone) -> assertFalse( userValidator.phoneChecker(phone))
        );
    }
}