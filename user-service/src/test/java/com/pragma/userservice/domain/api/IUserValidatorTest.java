package com.pragma.userservice.domain.api;

import com.pragma.userservice.infrastructure.driven_adapters.UserValidator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IUserValidatorTest {
    IUserValidator userValidator;

    @Test
    void emailChecker() {
        userValidator = new UserValidator();
        goodEmails();
        badEmails();
    }

    private void goodEmails() {
        List<String> emails = List.of("javaTpoint@domain.co.in", "12453@domain.com", "javaTpoint.name@domain.com",
                "1avaTpoint#@domain.co.in", "good@email");
        emails.forEach( (email) -> assertTrue(userValidator.emailChecker(email)));
    }

    private void badEmails() {
        List<String> emails = List.of("@yahoo.com", "hola", "bad@");
        emails.forEach( (email) -> assertFalse(userValidator.emailChecker(email)));
    }

    @Test
    void phoneChecker() {
        goodPhones();
        badPhones();
    }

    private void goodPhones() {

    }

    private void badPhones() {
        
    }
}