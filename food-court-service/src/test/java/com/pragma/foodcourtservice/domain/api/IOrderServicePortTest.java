package com.pragma.foodcourtservice.domain.api;

import com.pragma.foodcourtservice.FoodPlateData;
import com.pragma.foodcourtservice.RestaurantData;
import com.pragma.foodcourtservice.UserData;
import com.pragma.foodcourtservice.domain.exception.*;
import com.pragma.foodcourtservice.domain.model.*;
import com.pragma.foodcourtservice.domain.spi.IFoodPlatePersistencePort;
import com.pragma.foodcourtservice.domain.spi.IOrderPersistencePort;
import com.pragma.foodcourtservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourtservice.domain.useCase.OrderUseCase;
import com.pragma.foodcourtservice.infrastructure.exception.FoodPlateNotFoundException;
import com.pragma.foodcourtservice.infrastructure.output.jpa.adapter.WorkplaceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IOrderServicePortTest {
    IOrderPersistencePort orderPersistencePort;
    IFoodPlatePersistencePort foodPlatePersistencePort;
    IPersistentLoggedUser persistentLoggedUser;
    IRestaurantPersistencePort restaurantPersistencePort;
    IOrderNotifierPort orderNotifierPort;

    IOrderServicePort orderServicePort;
    @BeforeEach
    void setUp(){
        orderPersistencePort = mock(IOrderPersistencePort.class);
        foodPlatePersistencePort = mock(IFoodPlatePersistencePort.class);
        persistentLoggedUser = mock(IPersistentLoggedUser.class);
        restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        orderServicePort = new OrderUseCase(orderPersistencePort, foodPlatePersistencePort, persistentLoggedUser,
                restaurantPersistencePort, orderNotifierPort);
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
        getOrdersFilterByStateCorrectly();
        getOrdersFilterByStateButLoggedUserIsNotEmployee();
        getOrdersFilterByStateButIncorrectState();
    }

    @Test
    void getOrdersFilterByStateCorrectly() {
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        User employee = RestaurantData.EMPLOYEE;
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(employee);
        Integer state = OrderState.PENDING;
        Long idClient = UserData.CLIENT.getId();
        List<OrderWithFoodPlates> orders = new ArrayList<>(
                List.of(
                        new OrderWithFoodPlates(1l, idClient, LocalDate.now(), OrderState.PENDING, employee.getId(),
                                restaurant.getId(), List.of()),
                        new OrderWithFoodPlates(2l, idClient, LocalDate.now(), OrderState.READY, employee.getId(),
                                restaurant.getId(), List.of()),
                        new OrderWithFoodPlates(3l, idClient, LocalDate.now(), OrderState.PENDING, employee.getId(),
                                restaurant.getId(), List.of()),
                        new OrderWithFoodPlates(4l, idClient, LocalDate.now(), OrderState.CANCELLLED, employee.getId(),
                                restaurant.getId(), List.of()),
                        new OrderWithFoodPlates(5l, idClient, LocalDate.now(), OrderState.PENDING, employee.getId(),
                                restaurant.getId(), List.of())
                )
        );
        Integer page = 0;
        Integer limit = 3;
        for(int i = 0 ; i < page*limit ; ++i){
            orders.remove(0);
        }
        while(orders.size() > limit){
            orders.remove((int)limit);
        }
        when(restaurantPersistencePort.employeeWorkPlace(employee))
                .thenReturn(new RestaurantEmployee(restaurant.getId(), employee.getId()));
        when(orderPersistencePort.getOrdersFilterByState(state, restaurant.getId(), page, limit))
                .thenReturn(orders);
        assertEquals(
                orderServicePort.getOrdersFilterByState(state, page, limit),
                orders
        );
    }

    @Test
    void getOrdersFilterByStateButLoggedUserIsNotEmployee() {
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        User employee = UserData.CLIENT;
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(employee);
        Integer state = OrderState.PENDING;
        Long idClient = UserData.CLIENT.getId();
        List<OrderWithFoodPlates> orders = new ArrayList<>(
                List.of(
                        new OrderWithFoodPlates(1l, idClient, LocalDate.now(), OrderState.PENDING, employee.getId(),
                                restaurant.getId(), List.of()),
                        new OrderWithFoodPlates(2l, idClient, LocalDate.now(), OrderState.READY, employee.getId(),
                                restaurant.getId(), List.of()),
                        new OrderWithFoodPlates(3l, idClient, LocalDate.now(), OrderState.PENDING, employee.getId(),
                                restaurant.getId(), List.of()),
                        new OrderWithFoodPlates(4l, idClient, LocalDate.now(), OrderState.CANCELLLED, employee.getId(),
                                restaurant.getId(), List.of()),
                        new OrderWithFoodPlates(5l, idClient, LocalDate.now(), OrderState.PENDING, employee.getId(),
                                restaurant.getId(), List.of())
                )
        );
        Integer page = 0;
        Integer limit = 3;
        for(int i = 0 ; i < page*limit ; ++i){
            orders.remove(0);
        }
        while(orders.size() > limit){
            orders.remove((int)limit);
        }
        when(restaurantPersistencePort.employeeWorkPlace(employee))
                .thenReturn(new RestaurantEmployee(restaurant.getId(), employee.getId()));
        when(orderPersistencePort.getOrdersFilterByState(state, restaurant.getId(), page, limit))
                .thenReturn(orders);
        assertThrows(
                NotAnEmployeeException.class,
                ()->orderServicePort.getOrdersFilterByState(state, page, limit)
        );
    }

    @Test
    void getOrdersFilterByStateButIncorrectState() {
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        User employee = RestaurantData.EMPLOYEE;
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(employee);
        Integer state = -213213; //This state doesn't exist.
        Long idClient = UserData.CLIENT.getId();
        List<OrderWithFoodPlates> orders = new ArrayList<>(
                List.of(
                        new OrderWithFoodPlates(1l, idClient, LocalDate.now(), OrderState.PENDING, employee.getId(),
                                restaurant.getId(), List.of()),
                        new OrderWithFoodPlates(2l, idClient, LocalDate.now(), OrderState.READY, employee.getId(),
                                restaurant.getId(), List.of()),
                        new OrderWithFoodPlates(3l, idClient, LocalDate.now(), OrderState.PENDING, employee.getId(),
                                restaurant.getId(), List.of()),
                        new OrderWithFoodPlates(4l, idClient, LocalDate.now(), OrderState.CANCELLLED, employee.getId(),
                                restaurant.getId(), List.of()),
                        new OrderWithFoodPlates(5l, idClient, LocalDate.now(), OrderState.PENDING, employee.getId(),
                                restaurant.getId(), List.of())
                )
        );
        Integer page = 0;
        Integer limit = 3;
        for(int i = 0 ; i < page*limit ; ++i){
            orders.remove(0);
        }
        while(orders.size() > limit){
            orders.remove((int)limit);
        }
        when(restaurantPersistencePort.employeeWorkPlace(employee))
                .thenReturn(new RestaurantEmployee(restaurant.getId(), employee.getId()));
        when(orderPersistencePort.getOrdersFilterByState(state, restaurant.getId(), page, limit))
                .thenReturn(orders);
        assertThrows(
                InvalidStateException.class,
                ()->orderServicePort.getOrdersFilterByState(state, page, limit)
        );
    }


    @Test
    void assignToAnOrderCorrectly() {
        Long idOrder = 1l;
        User employee = RestaurantData.EMPLOYEE;
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Long idClient = UserData.CLIENT.getId();
        int state = OrderState.PENDING;
        Order order = new Order(idOrder, idClient, LocalDate.now(), state, employee.getId(),
                restaurant.getId());
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(employee);
        when(restaurantPersistencePort.employeeWorkPlace(employee))
                .thenReturn(new RestaurantEmployee(
                        restaurant.getId(),
                        employee.getId()
                ));
        when(orderPersistencePort.getOrder(idOrder))
                .thenReturn(order);
        assertDoesNotThrow(
                ()->orderServicePort.assignToAnOrder(idOrder)
        );
    }

    @Test
    void assignToAnOrderCorrectlyButIsNotLoggedAnEmployee() {
        Long idOrder = 1l;
        User employee = UserData.OWNER;
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Long idClient = UserData.CLIENT.getId();
        int state = OrderState.PENDING;
        Order order = new Order(idOrder, idClient, LocalDate.now(), state, employee.getId(),
                restaurant.getId());
        when(persistentLoggedUser.getLoggedUser())
                .thenThrow(NotAnEmployeeException.class);
        assertThrows(
                NotAnEmployeeException.class,
                ()->orderServicePort.assignToAnOrder(idOrder)
        );
    }

    @Test
    void assignToAnOrderCorrectlyButTheEmployeeDoesNotHaveWorkplace() {
        Long idOrder = 1l;
        User employee = RestaurantData.EMPLOYEE;
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Long idClient = UserData.CLIENT.getId();
        int state = OrderState.PENDING;
        Order order = new Order(idOrder, idClient, LocalDate.now(), state, employee.getId(),
                restaurant.getId());
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(employee);
        when(restaurantPersistencePort.employeeWorkPlace(employee))
                .thenThrow(WorkplaceNotFoundException.class);
        when(orderPersistencePort.getOrder(idOrder))
                .thenReturn(order);
        assertThrows(
                WorkplaceNotFoundException.class,
                ()->orderServicePort.assignToAnOrder(idOrder)
        );
    }

    @Test
    void assignToAnOrderCorrectlyButTheOrderIsFromOtherRestaurant() {
        Long idOrder = 1l;
        User employee = RestaurantData.EMPLOYEE;
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Long idClient = UserData.CLIENT.getId();
        int state = OrderState.PENDING;
        Order order = new Order(idOrder, idClient, LocalDate.now(), state, employee.getId(),
                23124l);
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(employee);
        when(restaurantPersistencePort.employeeWorkPlace(employee))
                .thenReturn(new RestaurantEmployee(
                        restaurant.getId(),
                        employee.getId()
                ));
        when(orderPersistencePort.getOrder(idOrder))
                .thenReturn(order);
        assertThrows(
                ForbiddenWorkInOrderException.class,
                ()->orderServicePort.assignToAnOrder(idOrder)
        );
    }

    @Test
    void assignToAnOrderCorrectlyButTheOrderIsNotPending() {
        Long idOrder = 1l;
        User employee = RestaurantData.EMPLOYEE;
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Long idClient = UserData.CLIENT.getId();
        int state = OrderState.READY;
        Order order = new Order(idOrder, idClient, LocalDate.now(), state, employee.getId(),
                restaurant.getId());
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(employee);
        when(restaurantPersistencePort.employeeWorkPlace(employee))
                .thenReturn(new RestaurantEmployee(
                        restaurant.getId(),
                        employee.getId()
                ));
        when(orderPersistencePort.getOrder(idOrder))
                .thenReturn(order);
        assertThrows(
                NotPendingOrderException.class,
                ()->orderServicePort.assignToAnOrder(idOrder)
        );
    }

    @Test
    void notifyTheOrderIsReady() {
    }
}