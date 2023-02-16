package com.pragma.foodcourtservice.application.handler;

import com.pragma.foodcourtservice.application.dto.RestaurantDTO;
import com.pragma.foodcourtservice.application.mapper.RestaurantDTOMapper;
import com.pragma.foodcourtservice.domain.api.IRestaurantServicePort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Implementation of the handler  interface to communicate RestaurantService and RestaurantServiceController. This uses
 * the restaurant service port and the mapper to RestaurantDTO (the input of the API) to Restaurant (input of model
 * layer).
 */
@Service
@Transactional
public class RestaurantHandler implements IRestaurantHandler {
    private final IRestaurantServicePort restaurantServicePort;
    private final RestaurantDTOMapper restaurantDtoMapper;

    public RestaurantHandler(IRestaurantServicePort restaurantServicePort, RestaurantDTOMapper restaurantDtoMapper) {
        this.restaurantServicePort = restaurantServicePort;
        this.restaurantDtoMapper = restaurantDtoMapper;
    }

    /**
     * Saves the data of a restaurant in the application. Map the data of the restaurant to register and calls the
     * service in charge of save the restaurant.
     * @param restaurant the DTO with the data of the owner to register.
     */
    @Override
    public void saveRestaurant(RestaurantDTO restaurant) {
        restaurantServicePort.saveRestaurant(restaurantDtoMapper.toRestaurant(restaurant));
    }
}
