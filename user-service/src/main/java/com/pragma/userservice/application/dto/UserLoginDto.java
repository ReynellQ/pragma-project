package com.pragma.userservice.application.dto;

/**
 * A DTO for the data to log in a user. It's the input for the API to log in.
 */
public class UserLoginDto {
    private String email;
    private String password;

    /**
     * Gets the email of the user.
     * @return a String containing the email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     * @param email the email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the password of the user.
     * @return a String containing the ciphered password.
     */
    public String getPassword() {
        return password;
    }
    /**
     * Sets the password of the user.
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
