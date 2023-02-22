package com.pragma.foodcourtservice.domain.api;

import com.pragma.foodcourtservice.application.dto.restaurant.RestaurantEmployeeDto;
import com.pragma.foodcourtservice.domain.model.Restaurant;
import com.pragma.foodcourtservice.domain.model.User;

import java.util.List;

/**
 * Interface IRestaurantServicePort that defines the port of the restaurant service and exposes the methods to the API.
 */
public interface IRestaurantServicePort {
    /**
     * Saves a restaurant in the application.
     * @param restaurant the restaurant to be saved.
     */
    void saveRestaurant(String emailCreator, Restaurant restaurant);

    /**
     * List an amount of restaurants corresponding to the <code>numberOfRestaurants</code>, sorted alphabetically, and
     * displaying the number of page provided.
     * @param numberOfRestaurants the number of restaurants displayed in the current page.
     * @param page the number of the page.
     * @return the list of restaurants.
     */
    List<Restaurant> listAllAlphabeticallyRestaurantsPaginated(int page, int numberOfRestaurants);

    /**
     * Saves an employee in the application.
     * @param emailCreator the creator's email
     * @param employee the employee user to register.
     * @param idRestaurant the restaurant of the owner/creator, and the restaurant where the employee works.
     */
    void saveAnEmployeeOfARestaurant(String emailCreator, User employee, Long idRestaurant);
}
