package com.pragma.foodcourtservice.domain.model;

/**
 * POJO class for Restaurant.
 */
public class Restaurant {
    private Long id;
    private String name;
    private String address;
    private Long idOwner;
    private String phone;
    private String urlLogo;
    private Long nit;

    public Restaurant() {
    }

    public Restaurant(Long id, String name, String address, Long idOwner, String phone, String urlLogo, Long nit) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.idOwner = idOwner;
        this.phone = phone;
        this.urlLogo = urlLogo;
        this.nit = nit;
    }
    /**
     * Gets the id of the restaurant
     * @return a Long value representing the id.
     */
    public Long getId() {
        return id;
    }
    /**
     * Sets the id of the restaurant
     * @param id the id of the restaurant.
     */
    public void setId(Long id) {
        this.id = id;
    }
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
     * Gets the owner's id of the restaurant
     * @return a Long value representing the id.
     */
    public Long getIdOwner() {
        return idOwner;
    }
    /**
     * Sets the owner's id of the restaurant
     * @param idOwner the owner's id of the restaurant.
     */
    public void setIdOwner(Long idOwner) {
        this.idOwner = idOwner;
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
