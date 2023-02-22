package com.pragma.foodcourtservice.application.dto.foodplate;
/**
 * A DTO to register a food plate.
 */
public class FoodPlateRegisterDto {
    private String name;
    private Long idCategory;
    private String description;
    private Long price;
    private Long idRestaurant;
    private String urlImage;


    /**
     * Gets the name of the food plate.
     * @return a String value representing the name.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the food plate.
     * @param name the name of the food plate.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets the category's id of the food plate.
     * @return a Long value representing the category's id.
     */
    public Long getIdCategory() {
        return idCategory;
    }
    /**
     * Sets the category's id of the food plate.
     * @param idCategory the category's id of the food plate.
     */
    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }
    /**
     * Gets the description of the food plate.
     * @return a String value representing the description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the description of the food plate.
     * @param description the description id of the food plate.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Gets the price of the food plate.
     * @return a Long value representing the price.
     */
    public Long getPrice() {
        return price;
    }

    /**
     * Sets the price of the food plate.
     * @param price the price of the food plate.
     */
    public void setPrice(Long price) {
        this.price = price;
    }
    /**
     * Gets the restaurant's id of the food plate.
     * @return a Long value representing the restaurant's id.
     */
    public Long getIdRestaurant() {
        return idRestaurant;
    }
    /**
     * Sets the restaurant's id of the food plate.
     * @param idRestaurant the restaurant's id of the food plate.
     */
    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }
    /**
     * Gets the image's url of the food plate.
     * @return a String value representing the image's url.
     */
    public String getUrlImage() {
        return urlImage;
    }
    /**
     * Sets the image's url of the food plate.
     * @param urlImage the image's url of the food plate.
     */
    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

}
