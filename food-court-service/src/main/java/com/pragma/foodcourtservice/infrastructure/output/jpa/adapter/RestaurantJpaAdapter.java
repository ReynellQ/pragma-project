package com.pragma.foodcourtservice.infrastructure.output.jpa.adapter;

import com.pragma.foodcourtservice.domain.model.Restaurant;
import com.pragma.foodcourtservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.RestauranteEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IRestaurantRepository;
/**
 * Adapter of the restaurant persistence port that implements the RestaurantPersistencePort.
 * Uses the Spring Jpa technology.
 */
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository restaurantRepository;
    private final RestauranteEntityMapper restaurantEntityMapper;

    public RestaurantJpaAdapter(IRestaurantRepository restaurantRepository,
                                RestauranteEntityMapper restaurantEntityMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantEntityMapper = restaurantEntityMapper;
    }
    /**
     * Saves a restaurant in the persistence layer. Saves in the JPA repository
     * @param restaurant the user to the saved.
     */
    @Override
    public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
    }
}
