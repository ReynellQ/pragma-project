package com.pragma.userservice.infrastructure.driven_adapters;

import com.pragma.userservice.domain.api.IAuthServicePort;
import com.pragma.userservice.infrastructure.configuration.jwt.JwtService;
import com.pragma.userservice.infrastructure.configuration.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Implementation of the interface IAuth with modules of Spring Security.
 */
@RequiredArgsConstructor
public class AuthServiceServicePort implements IAuthServicePort {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;


    /**
     * Encrypt a raw password and returns it. It uses the "bcrypt" algorithm provided by Spring Security.
     *
     * @param rawPassword the password to encrypt.
     * @return the password encrypted.
     */
    @Override
    public String encryptPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * Authenticates a user and returns the JWT to use the application. Calls the auth
     * @return a String representing the jwt.
     */
    @Override
    public String authenticateUser(String email, String password) {
        //Calls the authentication manager, that recieves the email and password, checks the email in database
        // and checks the passwords in the database and provided.
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );
        //Calls the userDetailsService, called in the authManager, in order to return the token to user.
        String jwtToken = jwtService.generateToken((UserDetailsImpl) auth.getPrincipal());
        return jwtToken;
    }
}
