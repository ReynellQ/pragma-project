package com.pragma.foodcourtservice.domain.api;

import com.pragma.foodcourtservice.domain.model.FoodPlate;

import java.util.List;

/**
 * Interface that defines the API port of the food court service and exposes the methods to the API.
 */
public interface IFoodPlateServicePort {
    /**
     * Saves a food plate in the application.
     * @param foodPlate the user to be saved.
     */
    void saveFoodPlate(FoodPlate foodPlate);
    /**
     * Updates a food plate in the application.
     * @param foodPlate the user to be updated.
     */
    FoodPlate updateFoodPlate(FoodPlate foodPlate);

    /**
     * Changed the state of a food plate, to the state of the food plate submitted.
     * TODO TESTING
     * @param foodPlate the food plate
     */
    void changeStateFoodPlate(FoodPlate foodPlate);

    /**
     * List all the food plates of a restaurant that have one of the submitted categories. If the list of categories
     * is empty, provide from all categories.
     * @param idRestaurant the id of the restaurant.
     * @param categories the list of the category's ids.
     * @param page the number of the page.
     * @param number the number of food plates displayed in the current page.
     *
     * @return a list with the food plates.
     */
    List<FoodPlate> listTheFoodPlatesByCategory(Long idRestaurant, List<Long> categories, int page, int number);
}
