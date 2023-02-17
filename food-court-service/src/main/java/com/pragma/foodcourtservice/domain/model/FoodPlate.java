package com.pragma.foodcourtservice.domain.model;

/**
 * POJO class for a food plate.
 */
public class FoodPlate {
    private Long id;
    private String name;
    private Long idCategory;
    private String description;
    private Long price;
    private Long idRestaurant;
    private String urlImage;
    private Boolean active;

    public FoodPlate() {
    }

    public FoodPlate(Long id, String name, Long idCategory, String description, Long price, Long idRestaurant, String urlImage, Boolean active) {
        this.id = id;
        this.name = name;
        this.idCategory = idCategory;
        this.description = description;
        this.price = price;
        this.idRestaurant = idRestaurant;
        this.urlImage = urlImage;
        this.active = active;
    }

    /**
     * Gets the id of the food plate.
     * @return a Long value representing the id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the food plate.
     * @param id the id of the food plate.
     */
    public void setId(Long id) {
        this.id = id;
    }
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
    /**
     * Gets if the food plate is active or not.
     * @return a boolean value.
     */
    public Boolean getActive() {
        return active;
    }
    /**
     * Sets if the food plate is active or not.
     * @param active the state of the food plate.
     */
    public void setActive(Boolean active) {
        this.active = active;
    }
}
