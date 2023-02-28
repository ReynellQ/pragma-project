package com.pragma.foodcourtservice.domain.api;

import com.pragma.foodcourtservice.FoodPlateData;
import com.pragma.foodcourtservice.RestaurantData;
import com.pragma.foodcourtservice.UserData;
import com.pragma.foodcourtservice.domain.exception.InvalidOrderException;
import com.pragma.foodcourtservice.domain.exception.NoPlatesException;
import com.pragma.foodcourtservice.domain.exception.NotAClientException;
import com.pragma.foodcourtservice.domain.model.Order;
import com.pragma.foodcourtservice.domain.model.OrderFoodPlates;
import com.pragma.foodcourtservice.domain.model.Restaurant;
import com.pragma.foodcourtservice.domain.spi.IFoodPlatePersistencePort;
import com.pragma.foodcourtservice.domain.spi.IOrderPersistencePort;
import com.pragma.foodcourtservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourtservice.domain.useCase.OrderUseCase;
import com.pragma.foodcourtservice.infrastructure.exception.FoodPlateNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IOrderServicePortTest {
    IOrderPersistencePort orderPersistencePort;
    IFoodPlatePersistencePort foodPlatePersistencePort;
    IPersistentLoggedUser persistentLoggedUser;
    IRestaurantPersistencePort restaurantPersistencePort;

    IOrderServicePort orderServicePort;
    @BeforeEach
    void setUp(){
        orderPersistencePort = mock(IOrderPersistencePort.class);
        foodPlatePersistencePort = mock(IFoodPlatePersistencePort.class);
        persistentLoggedUser = mock(IPersistentLoggedUser.class);
        restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        orderServicePort = new OrderUseCase(orderPersistencePort, foodPlatePersistencePort, persistentLoggedUser,
                restaurantPersistencePort);
    }

    @Test
    void saveOrderGoodOrder() {
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(UserData.CLIENT);
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Order order = new Order();
        order.setIdRestaurant(restaurant.getId());
        when(foodPlatePersistencePort.getFoodPlate(FoodPlateData.FOOD_PLATE_001.getId()))
                .thenReturn(FoodPlateData.FOOD_PLATE_001);
        List<OrderFoodPlates> foodPlatesList = List.of(
                new OrderFoodPlates(null, FoodPlateData.FOOD_PLATE_001.getId(), 1l)
        );
        assertDoesNotThrow(
                ()->orderServicePort.saveOrder(order, foodPlatesList)
        );
    }
    @Test
    void saveOrderNoPlatesInOrder() {
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(UserData.CLIENT);
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Order order = new Order();
        order.setIdRestaurant(restaurant.getId());
        when(foodPlatePersistencePort.getFoodPlate(FoodPlateData.FOOD_PLATE_001.getId()))
                .thenReturn(FoodPlateData.FOOD_PLATE_001);
        List<OrderFoodPlates> foodPlatesList = List.of(
        );
        assertThrows(
                NoPlatesException.class,
                ()->orderServicePort.saveOrder(order, foodPlatesList)
        );
    }
    @Test
    void saveOrderNonExistingFoodPlate() {
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(UserData.CLIENT);
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Order order = new Order();
        order.setIdRestaurant(restaurant.getId());
        when(foodPlatePersistencePort.getFoodPlate(FoodPlateData.FOOD_PLATE_001.getId()))
                .thenThrow(FoodPlateNotFoundException.class);
        List<OrderFoodPlates> foodPlatesList = List.of(
                new OrderFoodPlates(null, FoodPlateData.FOOD_PLATE_001.getId(), 1l)
        );
        assertThrows(
                FoodPlateNotFoundException.class,
                ()->orderServicePort.saveOrder(order, foodPlatesList)
        );
    }
    @Test
    void saveOrderForbiddenPlateInOrder() {
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(UserData.CLIENT);
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Order order = new Order();
        order.setIdRestaurant(restaurant.getId());
        when(foodPlatePersistencePort.getFoodPlate(FoodPlateData.INVALID_RESTAURANT_FOOD_PLATE.getId()))
                .thenReturn(FoodPlateData.INVALID_RESTAURANT_FOOD_PLATE);
        List<OrderFoodPlates> foodPlatesList = List.of(
                //This food plate has a different restaurant.
                new OrderFoodPlates(null, FoodPlateData.INVALID_RESTAURANT_FOOD_PLATE.getId(), 1l)
        );
        assertThrows(
                InvalidOrderException.class,
                ()->orderServicePort.saveOrder(order, foodPlatesList)
        );
    }
    @Test
    void saveOrderNegativeQuantityOfAFoodPlate() {
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(UserData.CLIENT);
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Order order = new Order();
        order.setIdRestaurant(restaurant.getId());
        when(foodPlatePersistencePort.getFoodPlate(FoodPlateData.FOOD_PLATE_001.getId()))
                .thenReturn(FoodPlateData.FOOD_PLATE_001);
        List<OrderFoodPlates> foodPlatesList = List.of(
                new OrderFoodPlates(null, FoodPlateData.FOOD_PLATE_001.getId(), -1l)
        );
        assertThrows(
                InvalidOrderException.class,
                ()->orderServicePort.saveOrder(order, foodPlatesList)
        );
    }
    @Test
    void saveOrderNoClientOrdering() {
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(UserData.OWNER);
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Order order = new Order();
        order.setIdRestaurant(restaurant.getId());
        when(foodPlatePersistencePort.getFoodPlate(FoodPlateData.FOOD_PLATE_001.getId()))
                .thenReturn(FoodPlateData.FOOD_PLATE_001);
        List<OrderFoodPlates> foodPlatesList = List.of(
                new OrderFoodPlates(null, FoodPlateData.FOOD_PLATE_001.getId(), 1l)
        );
        assertThrows(
                NotAClientException.class,
                ()->orderServicePort.saveOrder(order, foodPlatesList)
        );
    }

    @Test
    void getOrdersFilterByState() {
    }
}