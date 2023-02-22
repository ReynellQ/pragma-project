package com.pragma.foodcourtservice.application.dto.foodplate;

/**
 * A DTO to update a food plate.
 */
public class FoodPlateUpdateDto {
    private Long id;
    private String description;
    private Long price;


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


}
