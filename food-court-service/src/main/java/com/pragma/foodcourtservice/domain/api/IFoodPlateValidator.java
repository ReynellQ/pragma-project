package com.pragma.foodcourtservice.domain.api;

/**
 * Interface that has the validations of a food plate.
 */
public interface IFoodPlateValidator {
    /**
     * Validates if the restaurant exists.
     * @param id the restaurant's id
     * @return true if the restaurant exists, false in other case.
     */
    boolean validatesRestaurant(Long id);
    /**
     * Validates the price of the food plate.
     * @param price the price of the food plate
     * @return true if the price is valid, false in other case
     */
    boolean validatesPrice(Long price);
}
