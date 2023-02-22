package com.pragma.foodcourtservice.application.handler;

import com.pragma.foodcourtservice.application.dto.RestaurantClientResponse;
import com.pragma.foodcourtservice.application.dto.RestaurantCreateDto;
import com.pragma.foodcourtservice.application.mapper.RestaurantDtoMapper;
import com.pragma.foodcourtservice.domain.api.IRestaurantServicePort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the handler  interface to communicate RestaurantService and RestaurantServiceController. This uses
 * the restaurant service port and the mapper to RestaurantDTO (the input of the API) to Restaurant (input of model
 * layer).
 */
@Service
@Transactional
public class RestaurantHandler implements IRestaurantHandler {
    private final IRestaurantServicePort restaurantServicePort;
    private final RestaurantDtoMapper restaurantDtoMapper;

    public RestaurantHandler(IRestaurantServicePort restaurantServicePort, RestaurantDtoMapper restaurantDtoMapper) {
        this.restaurantServicePort = restaurantServicePort;
        this.restaurantDtoMapper = restaurantDtoMapper;
    }

    /**
     * Saves the data of a restaurant in the application. Map the data of the restaurant to register and calls the
     * service in charge of save the restaurant.
     * @param restaurant the DTO with the data of the owner to register.
     */
    @Override
    public void saveRestaurant(String email, RestaurantCreateDto restaurant) {
        restaurantServicePort.saveRestaurant(email, restaurantDtoMapper.toRestaurant(restaurant));
    }

    /**
     * List an amount of restaurants corresponding to the <code>numberOfRestaurants</code>, sorted alphabetically, and
     * displaying the number of page provided.
     *
     * @param numberOfRestaurant the number of restaurants displayed in the current page.
     * @param page               the number of the page.
     * @return the list of restaurants.
     */
    @Override
    public List<RestaurantClientResponse> listAllAlphabeticallyRestaurantsPaginated(int page, int numberOfRestaurant) {
        return restaurantServicePort.listAllAlphabeticallyRestaurantsPaginated(numberOfRestaurant, page)
                .stream()
                .map( (restaurant -> restaurantDtoMapper.toClientResponse(restaurant)))
                .collect(Collectors.toList());
    }
}
