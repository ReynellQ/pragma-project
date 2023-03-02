package com.pragma.foodcourtservice.domain.api;

import com.pragma.foodcourtservice.RestaurantData;
import com.pragma.foodcourtservice.UserData;
import com.pragma.foodcourtservice.domain.exception.RestaurantNotFoundException;
import com.pragma.foodcourtservice.domain.model.Order;
import com.pragma.foodcourtservice.domain.model.OrderState;
import com.pragma.foodcourtservice.domain.model.Restaurant;
import com.pragma.foodcourtservice.domain.model.User;
import com.pragma.foodcourtservice.domain.spi.IMessagingMicroServiceClientPort;
import com.pragma.foodcourtservice.domain.spi.IOrderPersistencePort;
import com.pragma.foodcourtservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourtservice.domain.spi.IUserMicroServiceClientPort;
import com.pragma.foodcourtservice.infrastructure.driven_adapter.OrderNotifierAdapter;
import com.pragma.foodcourtservice.infrastructure.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IOrderNotifierPortTest {
    IMessagingMicroServiceClientPort messagingMicroServiceClientPort;
    IUserMicroServiceClientPort userMicroServiceClientPort;
    IRestaurantPersistencePort restaurantPersistencePort;
    IOrderPersistencePort orderPersistencePort;

    IOrderNotifierPort orderNotifierPort;

    @BeforeEach
    void setUp(){
        messagingMicroServiceClientPort = mock(IMessagingMicroServiceClientPort.class);
        userMicroServiceClientPort = mock(IUserMicroServiceClientPort.class);
        restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        orderPersistencePort = mock(IOrderPersistencePort.class);
        orderNotifierPort = new OrderNotifierAdapter(messagingMicroServiceClientPort, userMicroServiceClientPort,
                restaurantPersistencePort, orderPersistencePort);
    }

    @Test
    void sendMessageCorrectly() {
        User employee = RestaurantData.EMPLOYEE;
        User client = UserData.CLIENT;
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Order order = new Order(1l, client.getId(), LocalDate.now(), OrderState.READY,
                employee.getId(), restaurant.getId());
        when(userMicroServiceClientPort.getUserById(client.getId()))
                .thenReturn(client);
        when(restaurantPersistencePort.getRestaurant(restaurant.getId()))
                .thenReturn(restaurant);
        assertDoesNotThrow( ()-> orderNotifierPort.sendMessage(order));
    }

    @Test
    void sendMessageButTheClientDoesNotExist() {
        User employee = RestaurantData.EMPLOYEE;
        User client = UserData.CLIENT;
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Order order = new Order(1l, client.getId(), LocalDate.now(), OrderState.READY,
                employee.getId(), restaurant.getId());
        when(userMicroServiceClientPort.getUserById(client.getId()))
                .thenThrow(UserNotFoundException.class);
        when(restaurantPersistencePort.getRestaurant(restaurant.getId()))
                .thenReturn(restaurant);
        assertThrows(
                UserNotFoundException.class,
                ()-> orderNotifierPort.sendMessage(order)
        );
    }

    @Test
    void sendMessageButTheRestaurantDoesNotExist() {
        User employee = RestaurantData.EMPLOYEE;
        User client = UserData.CLIENT;
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Order order = new Order(1l, client.getId(), LocalDate.now(), OrderState.READY,
                employee.getId(), restaurant.getId());
        when(userMicroServiceClientPort.getUserById(client.getId()))
                .thenReturn(client);
        when(restaurantPersistencePort.getRestaurant(restaurant.getId()))
                .thenThrow(RestaurantNotFoundException.class);
        assertThrows(
                RestaurantNotFoundException.class,
                ()-> orderNotifierPort.sendMessage(order)
        );
    }


    /**
     * The code has length of 4, and the characters are digits.
     */
    @Test
    void generateRandomCode() {
        String code = orderNotifierPort.generateRandomCode();
        assertEquals(code.length(), 4);
        for(Character c : code.toCharArray()){
            assertTrue( Character.isDigit(c));
        }
    }
}