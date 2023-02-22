package com.pragma.foodcourtservice.application.dto.restaurant;


public class RestaurantEmployeeDto {
    private Long personalId;
    private String name;
    private String lastname;
    private String phone;
    private String email;
    private String password;
    private Long idRestaurant;

    public RestaurantEmployeeDto() {
    }

    public RestaurantEmployeeDto(Long personalId, String name, String lastname, String phone, String email,
                                 String password, Long idRestaurant) {
        this.personalId = personalId;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.idRestaurant = idRestaurant;
    }

    public Long getPersonalId() {
        return personalId;
    }

    public void setPersonalId(Long personalId) {
        this.personalId = personalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }
}
