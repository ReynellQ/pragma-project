package com.pragma.foodcourtservice.domain.spi;

import com.pragma.foodcourtservice.domain.model.Restaurant;

/**
 * Interface port that defines the methods to communicate with the persistence layer in the domain layer.
 */
public interface IRestaurantPersistencePort {
    /**
     * Saves a restaurant in the persistence layer.
     * @param restaurant the restaurant to be saved.
     */
    void saveRestaurant(Restaurant restaurant);
}
