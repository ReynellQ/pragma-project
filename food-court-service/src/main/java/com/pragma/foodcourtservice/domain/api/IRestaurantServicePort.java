package com.pragma.foodcourtservice.domain.api;

import com.pragma.foodcourtservice.domain.model.Restaurant;

/**
 * Interface IRestaurantServicePort that defines the port of the restaurant service and exposes the methods to the API.
 */
public interface IRestaurantServicePort {
    /**
     * Saves a restaurant in the application.
     * @param restaurant the restaurant to be saved.
     */
    void saveRestaurant(Restaurant restaurant);
}
