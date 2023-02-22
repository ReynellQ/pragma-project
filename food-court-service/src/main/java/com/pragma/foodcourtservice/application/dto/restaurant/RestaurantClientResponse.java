package com.pragma.foodcourtservice.application.dto.restaurant;

public class RestaurantClientResponse {
    private String name;
    private String urlLogo;

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

}
