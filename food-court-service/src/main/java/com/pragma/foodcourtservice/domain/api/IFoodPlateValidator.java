package com.pragma.foodcourtservice.domain.api;

/**
 * Interface that has the validations of a food plate.
 */
public interface IFoodPlateValidator {
    /**
     * Validates the price of the food plate.
     * @param price the price of the food plate
     * @return true if the price is valid, false in other case
     */
    boolean validatesPrice(Long price);
}
