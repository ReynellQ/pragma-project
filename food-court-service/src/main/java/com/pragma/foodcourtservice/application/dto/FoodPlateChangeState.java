package com.pragma.foodcourtservice.application.dto;

public class FoodPlateChangeState {
    private Long id;
    private boolean active;

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
