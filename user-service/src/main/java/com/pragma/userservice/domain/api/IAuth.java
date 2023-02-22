package com.pragma.userservice.domain.api;

/**
 * Interface created with the methods needed to authentication.
 */
public interface IAuth {
    /**
     * Encrypt a raw password and returns it.
     * @param rawPassword the password to encrypt.
     * @return the password encrypted.
     */
    String encryptPassword(String rawPassword);
    /**
     * Compare a raw password and an encrypted password.
     * @param rawPassword the password to encrypt.
     * @return true if the raw password is the same that the encrypted password.
     */
    boolean comparePasswords(String rawPassword, String encryptedPassword);

    /**
     * Authenticates a user and returns the JWT to use the application.
     * @return a String representing the jwt.
     */
    String authenticateUser(String email, String password);
}
