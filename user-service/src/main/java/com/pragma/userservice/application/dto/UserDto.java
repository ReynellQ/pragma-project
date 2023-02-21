package com.pragma.userservice.application.dto;

/**
 * A DTO for User. It's the output of the API for User.
 */
public class UserDto {
    private Long id;
    private Long personalId;
    private String name;
    private String lastname;
    private String phone;
    private String email;
    private String password;
    private RoleDto role;
    /**
     * Gets the id of the user
     * @return a Long value representing the id.
     */

    public Long getPersonalId() {
        return personalId;
    }

    /**
     * Sets the id of the user
     * @param personalId the id of the user.
     */
    public void setPersonalId(Long personalId) {
        this.personalId = personalId;
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

    /**
     * Gets the user's role.
     * @return a Role value containing the role.
     */
    public RoleDto getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     * @param role the role's id of the user.
     */
    public void setRole(RoleDto role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
