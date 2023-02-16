package com.pragma.userservice;

import com.pragma.userservice.domain.api.IAuth;
import com.pragma.userservice.domain.api.IUserValidator;
import com.pragma.userservice.domain.spi.IUserPersistencePort;
import com.pragma.userservice.domain.useCase.UserUseCase;
import com.pragma.userservice.infrastructure.thirdparty.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class IAuthTests {
    IAuth auth;
    PasswordEncoder encoder;
    @BeforeEach
    void setUp(){
        encoder = new BCryptPasswordEncoder();
        auth = new AuthService(encoder);
    }

    /**
     * Encrypt a password and compare the raw password with the encrypted.
     */
    @Test
    void testCorrectPassword(){
        String password = "passwordRara";
        String encryptedPassword = auth.encryptPassword(password);
        assertTrue(password.compareTo(encryptedPassword) != 0); //Are different.
        assertTrue(auth.encryptPassword(password).compareTo(encryptedPassword) != 0); //Are different.
        //Despite being the two encrypt different, are the same original password.
        assertTrue(auth.comparePasswords(password, encryptedPassword));
    }

    /**
     * Encrypt a password and compare the encrypted password with another one string strictly different.
     */
    @Test
    void testIncorrectPassword(){
        String password = "passwordRara";
        String password2 = "otra cosa";

        String encryptedPassword = auth.encryptPassword(password);
        assertFalse(password.equals(password2)); //Are strictly different.
        assertTrue(password.compareTo(encryptedPassword) != 0);
        assertTrue(!password.equals(encryptedPassword)); //Are different.
        assertTrue(!auth.encryptPassword(password).equals(encryptedPassword)); //Are different.
        //Definitely are not the same
        assertFalse(auth.comparePasswords(password2, encryptedPassword));
    }
}
