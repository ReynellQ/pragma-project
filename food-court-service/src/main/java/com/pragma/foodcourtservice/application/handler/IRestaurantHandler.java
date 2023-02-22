package com.pragma.foodcourtservice.application.handler;

import com.pragma.foodcourtservice.application.dto.restaurant.RestaurantClientResponse;
import com.pragma.foodcourtservice.application.dto.restaurant.RestaurantCreateDto;
import com.pragma.foodcourtservice.application.dto.restaurant.RestaurantEmployeeDto;

import java.util.List;

/**
 * Handler interface to communicate RestaurantService and RestaurantRestController.
 */
public interface IRestaurantHandler {
    /**
     * Saves the data of a restaurant in the application.
     * @param emailCreator the email of the creator.
     * @param restaurant the DTO with the data of the restaurant to register.
     */
    void saveRestaurant(String emailCreator, RestaurantCreateDto restaurant);

    /**
     * List an amount of restaurants corresponding to the <code>numberOfRestaurants</code>, sorted alphabetically, and
     * displaying the number of page provided.
     * @param numberOfRestaurant the number of restaurants displayed in the current page.
     * @param page the number of the page.
     * @return the list of restaurants.
     */
    List<RestaurantClientResponse> listAllAlphabeticallyRestaurantsPaginated( int page, int numberOfRestaurant);

    /**
     * Saves the data of a restaurant's employee in the application
     * @param restaurantEmployeeDto the data of the employee
     */
    void saveAnEmployeeOfARestaurant(String emailCreator, RestaurantEmployeeDto restaurantEmployeeDto);
}
