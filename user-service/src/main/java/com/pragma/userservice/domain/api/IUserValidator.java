package com.pragma.userservice.domain.api;

public interface IUserValidator {
    /**
     * Checks if the string email is a valid email.
     * @param email
     * @return true if is valid, false in the other case.
     */
    boolean emailChecker(String email);
    /**
     * Checks if the string phone is a valid phone.
     * @param phone
     * @return true if is valid, false in the other case.
     */
    boolean phoneChecker(String phone);
}
