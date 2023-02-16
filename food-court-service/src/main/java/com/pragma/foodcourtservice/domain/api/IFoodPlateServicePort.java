package com.pragma.foodcourtservice.domain.api;

import com.pragma.foodcourtservice.domain.model.FoodPlate;
/**
 * Interface that defines the API port of the food court service and exposes the methods to the API.
 */
public interface IFoodPlateServicePort {
    /**
     * Saves a food plate in the application.
     * @param foodPlate the user to be saved.
     */
    void savePlato(FoodPlate foodPlate);
    /**
     * Updates a food plate in the application.
     * @param foodPlate the user to be updated.
     */
    FoodPlate updatePlato(FoodPlate foodPlate);
}
