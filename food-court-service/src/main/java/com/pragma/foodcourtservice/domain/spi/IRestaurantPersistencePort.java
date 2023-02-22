package com.pragma.foodcourtservice.domain.spi;

import com.pragma.foodcourtservice.domain.model.Restaurant;

import java.util.List;

/**
 * Interface port that defines the methods to communicate with the persistence layer in the domain layer.
 */
public interface IRestaurantPersistencePort {
    /**
     * Saves a restaurant in the persistence layer.
     * @param restaurant the restaurant to be saved.
     */
    void saveRestaurant(Restaurant restaurant);

    /**
     * Get a restaurant with the provided id.
     * @param id the restaurant's id
     * @return a restaurant with the id provided.
     */
    Restaurant getRestaurant(Long id);

    /**
     * List an amount of restaurants corresponding to the <code>numberOfRestaurants</code>, sorted alphabetically, and
     * displaying the number of page provided.
     * @param numberOfRestaurants the number of restaurants displayed in the current page.
     * @param page the number of the page.
     * @return the list of restaurants.
     */
    List<Restaurant> listAllAlphabeticallyRestaurantsPaginated( int page, int numberOfRestaurants);
}
