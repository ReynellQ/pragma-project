package com.pragma.foodcourtservice.infrastructure.output.jpa.adapter;

import com.pragma.foodcourtservice.domain.exception.RestaurantDoesntExistsException;
import com.pragma.foodcourtservice.domain.model.Restaurant;
import com.pragma.foodcourtservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IRestaurantRepository;

import java.util.Optional;

/**
 * Adapter of the restaurant persistence port that implements the RestaurantPersistencePort.
 * Uses the Spring Jpa technology.
 */
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;

    public RestaurantJpaAdapter(IRestaurantRepository restaurantRepository,
                                RestaurantEntityMapper restaurantEntityMapper) {
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

    /**
     * Get a restaurant with the provided id. Search in the repository with Jpa, map it and returns it.
     *
     * @param id the restaurant's id
     * @return a restaurant with the id provided.
     * @throws RestaurantDoesntExistsException if doesn't exist a restaurant with the provided id.
     */
    @Override
    public Restaurant getRestaurant(Long id) {
        Optional<RestaurantEntity> restaurantEntity = restaurantRepository.findById(id);
        if(restaurantEntity.isEmpty())
            throw new RestaurantDoesntExistsException();
        return restaurantEntityMapper.toRestaurante(restaurantEntity.get());
    }
}
