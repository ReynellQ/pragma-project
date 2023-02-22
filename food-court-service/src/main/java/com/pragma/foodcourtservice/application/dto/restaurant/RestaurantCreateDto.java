package com.pragma.foodcourtservice.application.dto.restaurant;

/**
 * A DTO for User. It's the output of the API for Food Court.
 */
public class RestaurantCreateDto {
    private String name;
    private String address;
    private String phone;
    private String urlLogo;
    private Long nit;

    /**
     * Gets the name of the restaurant
     * @return a String value representing the name.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the restaurant
     * @param name the name of the restaurant.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets the address of the restaurant
     * @return a String value representing the address.
     */
    public String getAddress() {
        return address;
    }
    /**
     * Sets the address of the restaurant
     * @param address the address of the restaurant.
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Gets the phone of the restaurant
     * @return a String value representing the phone.
     */
    public String getPhone() {
        return phone;
    }
    /**
     * Sets the phone of the restaurant
     * @param phone the phone of the restaurant.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**
     * Gets the logo's url of the restaurant
     * @return a String value representing the logo's url.
     */
    public String getUrlLogo() {
        return urlLogo;
    }
    /**
     * Sets the logo's url of the restaurant
     * @param urlLogo the logo's url of the restaurant.
     */
    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }
    /**
     * Gets the nit of the restaurant
     * @return a Long value representing the nit of the restaurant.
     */
    public Long getNit() {
        return nit;
    }
    /**
     * Sets the nit of the restaurant
     * @param nit the nit of the restaurant.
     */
    public void setNit(Long nit) {
        this.nit = nit;
    }
}
