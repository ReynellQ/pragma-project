package com.pragma.foodcourtservice;

import com.pragma.foodcourtservice.domain.model.Restaurant;
import com.pragma.foodcourtservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourtservice.infrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.RestaurantEntityMapperImpl;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IRestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.mock;

/**
 * Not implemented.
 */
@SpringBootTest
public class IRestaurantPersistencePortTests {
    RestaurantEntityMapper restaurantEntityMapper;
    IRestaurantRepository restaurantRepository;
    IRestaurantPersistencePort restaurantPersistencePort;

    @BeforeEach
    void setUp(){
        restaurantEntityMapper = new RestaurantEntityMapperImpl();
        restaurantRepository = mock(IRestaurantRepository.class);
        restaurantPersistencePort = new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
    }

    /**
     * Saves a restaurant.
     * At this layer, there is no validations. Only verifies that the repository works well.
     */
    @Test
    void saveARestaurant(){
        Restaurant r1 = RestaurantData.NON_INSERTED_RESTAURANT;
        throw new RuntimeException();
    }
}
