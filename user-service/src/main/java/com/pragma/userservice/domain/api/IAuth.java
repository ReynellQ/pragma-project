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
}
