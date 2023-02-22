package com.pragma.foodcourtservice.application.handler;

import com.pragma.foodcourtservice.application.dto.FoodPlateChangeState;
import com.pragma.foodcourtservice.application.dto.FoodPlateRegisterDto;
import com.pragma.foodcourtservice.application.dto.FoodPlateUpdateDto;

/**
 * Handler interface to communicate FoodPlateService and FoodPlateRestController.
 */
public interface IFoodPlateHandler {
    /**
     * Saves the data of a food plate in the application.
     * @param email the email of the creator.
     * @param foodPlate the DTO with the data of the food plate to register.
     */
    void saveFoodPlate(String email, FoodPlateRegisterDto foodPlate);
    /**
     * Updates and saves the data of a food plate in the application.
     * @param email the email of the owner.
     * @param foodPlate the DTO with the data of the food plate to update.
     */
    void updateFoodPlate(String email, FoodPlateUpdateDto foodPlate);

    /**
     * Changed the state of a food plate in the applicaction.
     * TODO TESTING
     * @param changeState the DTO with the new state and the id of the food plate to change.
     */
    void changeStateFoodPlate(FoodPlateChangeState changeState);
}
