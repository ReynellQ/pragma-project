package com.pragma.foodcourtservice.application.handler;

import com.pragma.foodcourtservice.application.dto.RestaurantDTO;

/**
 * Handler interface to communicate RestaurantService and RestaurantRestController.
 */
public interface IRestaurantHandler {
    /**
     * Saves the data of a restaurant in the application.
     * @param restaurant the DTO with the data of the restaurant to register.
     */
    void saveRestaurant(RestaurantDTO restaurant);
}
