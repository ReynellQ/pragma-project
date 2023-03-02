package com.pragma.userservice.domain.api;

import com.pragma.userservice.infrastructure.configuration.UserDetailsImpl;
import com.pragma.userservice.infrastructure.configuration.jwt.JwtService;
import com.pragma.userservice.infrastructure.driven_adapters.AuthServiceServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IAuthServicePortTest {
    PasswordEncoder passwordEncoder;
    JwtService jwtService;
    AuthenticationManager authManager;
    IAuthServicePort authServicePort;
    @BeforeEach
    void setUp(){
        passwordEncoder = mock(PasswordEncoder.class);
        jwtService = mock(JwtService.class);
        authManager = mock(AuthenticationManager.class);
        authServicePort = new AuthServiceServicePort(passwordEncoder, jwtService, authManager);
    }

    @Test
    void encryptPassword() {
        String password = "password";
        String encryptedPassword = "askdkasdd";
        when(passwordEncoder.encode(password)).thenReturn(encryptedPassword);

        assertEquals(authServicePort.encryptPassword(password), encryptedPassword);
    }

    @Test
    void goodCredentials() {
        String user = "gooduser@gmail.com";
        String password = "correctPassword";
        String imaginaryJwt = "bXl1c2VyaXNub3dhdXRoZW50aWNhdGVk";
        Authentication auth = mock(Authentication.class);
        UserDetailsImpl userDetails = mock(UserDetailsImpl.class);
        when(auth.getPrincipal()).thenReturn(userDetails);
        when(authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user,
                        password
                )
        )).thenReturn(auth);
        when(jwtService.generateToken(userDetails))
                .thenReturn("bXl1c2VyaXNub3dhdXRoZW50aWNhdGVk");
        assertEquals(authServicePort.authenticateUser(user, password), imaginaryJwt);
    }

    @Test
    void badCredentials() {
        String user = "gooduser@gmail.com";
        String password = "badPassword";
        doThrow(BadCredentialsException.class).when(authManager).authenticate(
                new UsernamePasswordAuthenticationToken(
                        user,
                        password
                )
        );
        assertThrows(BadCredentialsException.class, ()-> authServicePort.authenticateUser(user, password));
    }
}