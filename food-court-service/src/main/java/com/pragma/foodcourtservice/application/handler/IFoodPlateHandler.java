package com.pragma.foodcourtservice.application.handler;

import com.pragma.foodcourtservice.application.dto.foodplate.*;

import java.util.List;

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
     * Changed the state of a food plate in the application.
     * TODO TESTING
     * @param changeState the DTO with the new state and the id of the food plate to change.
     */
    void changeStateFoodPlate(String email, FoodPlateChangeState changeState);

    /**
     * List all the food plates of a restaurant that have one of the submitted categories. If the list of categories
     * is empty, provide from all categories.
     * @param idRestaurant the id of the restaurant.
     * @param categories the list of the category's ids.
     * @param number the number of food plates displayed in the current page.
     * @param page the number of the page.
     * @return a list with the food plates.
     */
    List<FoodPlateDto> listTheFoodPlatesByCategory(Long idRestaurant, CategoryList categories, int page,
                                                   int number);
}
