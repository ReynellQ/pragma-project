package com.pragma.userservice.infrastructure.driven_adapters;

import com.pragma.userservice.domain.api.IAuth;
import com.pragma.userservice.infrastructure.configuration.jwt.JwtService;
import com.pragma.userservice.infrastructure.configuration.UserDetailsImpl;
import com.pragma.userservice.infrastructure.configuration.UserDetailsServiceImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Implementation of the interface IAuth with modules of Spring Security.
 */
public class AuthService implements IAuth {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;

    public AuthService(PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

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
     * Compare a raw password and an encrypted password.
     *
     * @param rawPassword       the password to encrypt.
     * @param encryptedPassword
     * @return true if the raw password is the same that the encrypted password.
     */
    @Override
    public boolean comparePasswords(String rawPassword, String encryptedPassword) {
        return passwordEncoder.matches(rawPassword, encryptedPassword);
    }

    @Override
    public String authenticateUser(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );
        String jwtToken = jwtService.generateToken((UserDetailsImpl) userDetailsService.loadUserByUsername(email));
        return jwtToken;
    }
}
