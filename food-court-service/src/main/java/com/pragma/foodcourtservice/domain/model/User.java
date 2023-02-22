package com.pragma.foodcourtservice.domain.model;

/**
 * POJO class for User. Represents a User in the Model layer.
 *
 */
public class User {
    private Long id;
    private Long personalId;
    private String name;
    private String lastname;
    private String phone;
    private String email;
    private String password;
    private Integer idRole;

    public User() {
    }

    public User(Long id, Long personalId, String name, String lastname, String phone, String email, String password,
                Integer idRole) {
        this.id = id;
        this.personalId = personalId;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.idRole = idRole;
    }

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
     * Gets the user's role's id, that is numeric and mapped from the database.
     * @return an Integer containing the role.
     */
    public Integer getIdRole() {
        return idRole;
    }

    /**
     * Sets the role's id of the user.
     * @param idRole the role's id of the user.
     */
    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
