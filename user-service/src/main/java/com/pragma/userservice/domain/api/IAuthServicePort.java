package com.pragma.userservice.domain.api;

/**
 * Interface created with the methods needed to authentication.
 */
public interface IAuthServicePort {
    /**
     * Encrypt a raw password and returns it.
     * @param rawPassword the password to encrypt.
     * @return the password encrypted.
     */
    String encryptPassword(String rawPassword);

    /**
     * Authenticates a user and returns the JWT to use the application.
     * @return a String representing the jwt.
     */
    String authenticateUser(String email, String password);
}
