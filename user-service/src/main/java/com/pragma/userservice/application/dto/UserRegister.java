package com.pragma.userservice.application.dto;

/**
 * A DTO to register a User, containing the needed data. It's the input of the API for register.
 */
public class UserRegister {
    private Long id;
    private String name;
    private String lastname;
    private String phone;
    private String email;
    private String password;

    /**
     * Gets the id of the user
     * @return a Long value representing the id.
     */

    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the user
     * @param id the id of the user.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the name of the user.
     * @return a String containing the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     * @param name the name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the lastname of the user.
     * @return a String containing the lastname.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the lastname of the user.
     * @param lastname the lastname of the user.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Gets the phone of the user.
     * @return a String containing the phone.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone of the user.
     * @param phone the phone of the user.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

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
     * Gets the ciphered password of the user.
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
