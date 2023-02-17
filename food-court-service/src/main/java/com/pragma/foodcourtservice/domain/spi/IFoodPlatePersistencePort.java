package com.pragma.foodcourtservice.domain.spi;

import com.pragma.foodcourtservice.domain.model.FoodPlate;

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

}
