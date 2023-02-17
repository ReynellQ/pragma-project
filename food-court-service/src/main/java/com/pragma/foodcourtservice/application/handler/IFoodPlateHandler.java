package com.pragma.foodcourtservice.application.handler;

import com.pragma.foodcourtservice.application.dto.FoodPlateRegisterDto;
import com.pragma.foodcourtservice.application.dto.FoodPlateUpdateDto;

/**
 * Handler interface to communicate FoodPlateService and FoodPlateRestController.
 */
public interface IFoodPlateHandler {
    /**
     * Saves the data of a food plate in the application.
     * @param foodPlate the DTO with the data of the food plate to register.
     */
    void saveFoodPlate(FoodPlateRegisterDto foodPlate);
    /**
     * Updates and saves the data of a food plate in the application.
     * @param foodPlate the DTO with the data of the food plate to update.
     */
    void updateFoodPlate(FoodPlateUpdateDto foodPlate);
}
