package com.pragma.userservice.infrastructure.thirdparty;

import com.pragma.userservice.domain.api.IAuth;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Implementation of the interface IAuth with modules of Spring Security.
 */
public class AuthService implements IAuth {
    private final PasswordEncoder passwordEncoder;

    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
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
}
