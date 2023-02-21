package com.pragma.foodcourtservice.domain.api;

import com.pragma.foodcourtservice.domain.model.Restaurant;

import java.util.List;

/**
 * Interface IRestaurantServicePort that defines the port of the restaurant service and exposes the methods to the API.
 */
public interface IRestaurantServicePort {
    /**
     * Saves a restaurant in the application.
     * @param restaurant the restaurant to be saved.
     */
    void saveRestaurant(String email, Restaurant restaurant);

    /**
     * List an amount of restaurants corresponding to the <code>numberOfRestaurants</code>, sorted alphabetically, and
     * displaying the number of page provided.
     * @param numberOfRestaurants the number of restaurants displayed in the current page.
     * @param page the number of the page.
     * @return the list of restaurants.
     */
    List<Restaurant> listAllAlphabeticallyRestaurantsPaginated(int page, int numberOfRestaurants);
}
