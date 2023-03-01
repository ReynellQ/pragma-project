package com.pragma.foodcourtservice.infrastructure.output.jpa.adapter;

import com.pragma.foodcourtservice.domain.exception.RestaurantNotFoundException;
import com.pragma.foodcourtservice.domain.model.Restaurant;
import com.pragma.foodcourtservice.domain.model.RestaurantEmployee;
import com.pragma.foodcourtservice.domain.model.User;
import com.pragma.foodcourtservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.RestaurantEmployeeEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.RestaurantEmployeeEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IRestaurantEmployeeRepository;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adapter of the restaurant persistence port that implements the RestaurantPersistencePort.
 * Uses the Spring Jpa technology.
 */
@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEmployeeRepository restaurantEmployeeRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final RestaurantEmployeeEntityMapper restaurantEmployeeEntityMapper;

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
     * @throws RestaurantNotFoundException if doesn't exist a restaurant with the provided id.
     */
    @Override
    public Restaurant getRestaurant(Long id) {
        Optional<RestaurantEntity> restaurantEntity = restaurantRepository.findById(id);
        if(restaurantEntity.isEmpty())
            throw new RestaurantNotFoundException();
        return restaurantEntityMapper.toRestaurant(restaurantEntity.get());
    }

    /**
         * TODO TESTING
     * List an amount of restaurants corresponding to the <code>numberOfRestaurants</code>, sorted alphabetically, and
     * displaying the number of page provided.
     *
     * @param numberOfRestaurant the number of restaurants displayed in the current page.
     * @param page               the number of the page.
     * @return the list of restaurants.
     */
    @Override
    public List<Restaurant> listAllAlphabeticallyRestaurantsPaginated(int page, int numberOfRestaurant) {
        return restaurantRepository.findAllSortedByNameAndPaginated(page*numberOfRestaurant, numberOfRestaurant)
                .stream().map( (entity) -> restaurantEntityMapper.toRestaurant(entity))
                .collect(Collectors.toList());
    }

    /**
     * Register an existing employee in a restaurant.
     *
     * @param restaurantEmployee the restaurant's id and the employee's id
     */
    @Override
    public void registerAnEmployee(RestaurantEmployee restaurantEmployee) {
        RestaurantEmployeeEntity entity = restaurantEmployeeEntityMapper.toEntity(restaurantEmployee);
        restaurantEmployeeRepository.save(entity);
    }

    /**
     * Search and returns the data of the restaurant of the employee submited.
     *
     * @param employee the data of the employee.
     * @return an <code>RestaurantEmployee</code> with the data of the employee and its restaurant.
     */
    @Override
    public RestaurantEmployee employeeWorkPlace(User employee) {
        Optional<RestaurantEmployeeEntity> entity = restaurantEmployeeRepository.findByIdIdUser(employee.getId());
        if(entity.isEmpty()){
            throw new WorkplaceNotFoundException();
        }
        return restaurantEmployeeEntityMapper
                .toRestaurantEmployee(entity.get());
    }

}
