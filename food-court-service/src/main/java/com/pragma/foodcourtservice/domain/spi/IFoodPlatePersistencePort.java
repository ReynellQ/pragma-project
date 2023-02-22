package com.pragma.foodcourtservice.domain.spi;

import com.pragma.foodcourtservice.domain.model.FoodPlate;

import java.util.List;

/**
 * Interface port that defines the methods to communicate with the Food plate entity on persistence layer and the Food
 * plate model in the domain layer.
 */
public interface IFoodPlatePersistencePort {
    /**
     * Saves a food plate in the persistence layer.
     * @param foodPlate the food plate to be saved.
     */
    void saveFoodPlate(FoodPlate foodPlate);
    /**
     * Updates a food plate in the persistence layer.
     * @param foodPlate the food plate to be updated.
     * @return the food plate updated.
     */
    FoodPlate updateFoodPlate(FoodPlate foodPlate);
    /**
     * Gets a food plate from the persistence layer given its id.
     * @param id the food plate's id to be searched.
     * @return The food plate obtained.
     */
    FoodPlate getFoodPlate(Long id);


    /**
     * Gets a list from the persistence layer of all the food plates of a restaurant that have one of the submitted
     * categories. If the list of categories is empty, provide from all categories.
     * @param idRestaurant the id of the restaurant.
     * @param categories the list of the category's ids.
     * @param page the number of the page.
     * @param number the number of food plates displayed in the current page.
     *
     * @return a list with the food plates.
     */
    List<FoodPlate> listTheFoodPlatesByCategory(Long idRestaurant, List<Long> categories, int page, int number);
}
