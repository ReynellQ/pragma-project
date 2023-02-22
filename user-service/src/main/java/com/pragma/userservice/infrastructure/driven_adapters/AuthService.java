package com.pragma.userservice.infrastructure.driven_adapters;

import com.pragma.userservice.domain.api.IAuth;
import com.pragma.userservice.infrastructure.configuration.jwt.JwtService;
import com.pragma.userservice.infrastructure.configuration.UserDetailsImpl;
import com.pragma.userservice.infrastructure.configuration.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Implementation of the interface IAuth with modules of Spring Security.
 */
@RequiredArgsConstructor
public class AuthService implements IAuth {
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final UserDetailsServiceImpl userDetailsService;


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
    /**
     * Authenticates a user and returns the JWT to use the application. Calls the auth
     * @return a String representing the jwt.
     */
    @Override
    public String authenticateUser(String email, String password) {
        //Calls the authentication manager, that recieves the email and password, checks the email in database
        // and checks the passwords in the database and provided.
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );
        //Calls the userDetailsService, called in the authManager, in order to return the token to user.
        //It's not necessary because the token is already in header, but to return to user it's.
        String jwtToken = jwtService.generateToken((UserDetailsImpl) userDetailsService.loadUserByUsername(email));
        return jwtToken;
    }
}
