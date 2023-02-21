package com.pragma.foodcourtservice.domain.api;

import com.pragma.foodcourtservice.domain.model.FoodPlate;
/**
 * Interface that defines the API port of the food court service and exposes the methods to the API.
 */
public interface IFoodPlateServicePort {
    /**
     * Saves a food plate in the application.
     * @param email the email of the creator.
     * @param foodPlate the user to be saved.
     */
    void saveFoodPlate(String email, FoodPlate foodPlate);
    /**
     * Updates a food plate in the application.
     * @param email the email of the updater.
     * @param foodPlate the user to be updated.
     */
    FoodPlate updateFoodPlate(String email, FoodPlate foodPlate);

    /**
     * Changed the state of a food plate, to the state of the food plate submitted.
     * TODO TESTING
     * @param foodPlate the food plate
     */
    void changeStateFoodPlate(FoodPlate foodPlate);
}
