package com.pragma.foodcourtservice.domain.spi;

import com.pragma.foodcourtservice.FoodPlateData;
import com.pragma.foodcourtservice.RestaurantData;
import com.pragma.foodcourtservice.UserData;
import com.pragma.foodcourtservice.domain.model.*;
import com.pragma.foodcourtservice.infrastructure.output.jpa.adapter.OrderJpaAdapter;
import com.pragma.foodcourtservice.infrastructure.exceptionhandler.OrderNotFoundException;
import com.pragma.foodcourtservice.infrastructure.exception.OrderTicketNotFoundException;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.OrderEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.OrderFoodPlatesEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.OrderTicketEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.pk.OrderFoodPlatesEntityID;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.OrderEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.OrderTicketEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IOrderFoodPlatesRepository;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IOrderRepository;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IOrderTicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IOrderPersistencePortTest {
    IOrderRepository orderRepository;
    IOrderTicketRepository orderTicketRepository;
    IOrderFoodPlatesRepository orderFoodPlatesRepository;
    OrderEntityMapper orderEntityMapper;
    OrderTicketEntityMapper orderTicketEntityMapper;

    IOrderPersistencePort orderPersistencePort;
    @BeforeEach
    void setUp(){
        orderRepository = mock(IOrderRepository.class);
        orderTicketRepository = mock(IOrderTicketRepository.class);
        orderFoodPlatesRepository = mock(IOrderFoodPlatesRepository.class);
        orderEntityMapper = mock(OrderEntityMapper.class);
        orderTicketEntityMapper = mock(OrderTicketEntityMapper.class);

        orderPersistencePort = new OrderJpaAdapter(orderRepository,orderTicketRepository, orderFoodPlatesRepository,
                orderEntityMapper, orderTicketEntityMapper);

    }
    @Test
    void saveOrder() {
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        User client = UserData.CLIENT;
        Order order = new Order(null, client.getId(), LocalDate.now(), OrderState.PENDING, null, restaurant.getId());
        List<OrderFoodPlates> foodPlatesList = List.of(
                new OrderFoodPlates(null, FoodPlateData.FOOD_PLATE_001.getId(), 1l)
        );
        order.setIdRestaurant(restaurant.getId());
        OrderEntity orderEntity = new OrderEntity(null, client.getId(), LocalDate.now(), OrderState.PENDING,
                null, restaurant.getId(), null, null);
        Long orderId = 239213l;
        OrderEntity orderEntitySaved = new OrderEntity(orderId, client.getId(), LocalDate.now(), OrderState.PENDING,
                null, restaurant.getId(), null, null);

        when(orderEntityMapper.toEntity(order))
                .thenReturn(orderEntity);
        when(orderRepository.save(orderEntity))
                .thenReturn(orderEntitySaved);
        foodPlatesList.forEach(
                (
                        foodPlate -> when(orderEntityMapper.toEntity(foodPlate))
                                .thenReturn( new OrderFoodPlatesEntity(
                                        new OrderFoodPlatesEntityID(foodPlate.getIdOrder(), foodPlate.getIdFoodPlate()),
                                        foodPlate.getQuantity(),
                                        null,
                                        null
                                ))
                )
        );
        assertDoesNotThrow(
                ()->orderPersistencePort.saveOrder(order, foodPlatesList)
        );
    }

    @Test
    void getOrdersFilterByState() {
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        User employee = RestaurantData.EMPLOYEE;
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

        List<OrderEntity> ordersEntity = new ArrayList<>(
                List.of(
                        new OrderEntity(1l, idClient, LocalDate.now(), OrderState.PENDING, restaurant.getId(),
                                employee.getId(), null, List.of()),
                        new OrderEntity(2l, idClient, LocalDate.now(), OrderState.READY, restaurant.getId(),employee.getId(),
                                null, List.of()),
                        new OrderEntity(3l, idClient, LocalDate.now(), OrderState.PENDING,restaurant.getId(),  employee.getId(),
                                null, List.of()),
                        new OrderEntity(4l, idClient, LocalDate.now(), OrderState.CANCELLLED,restaurant.getId(), employee.getId(),
                                null, List.of()),
                        new OrderEntity(5l, idClient, LocalDate.now(), OrderState.PENDING, restaurant.getId(), employee.getId(),
                                null, List.of())
                )
        );
        Integer page = 0;
        Integer limit = 3;
        for(int i = 0 ; i < page*limit && !orders.isEmpty() ; ++i){
            orders.remove(0);
            ordersEntity.remove(0);
        }
        while(orders.size() > limit){
            orders.remove((int)limit);
            ordersEntity.remove((int)limit);
        }
        Long idRestaurant = restaurant.getId();
        when(orderRepository.findTheOrdersFromRestaurantAndState(state, idRestaurant, page*limit, limit))
                .thenReturn(ordersEntity);
        for(int i = 0 ; i < ordersEntity.size() ; ++i){
            when(orderEntityMapper.toOrderWithFoodPlates(ordersEntity.get(i)))
                    .thenReturn(orders.get(i));
        }
        assertEquals(
                orderPersistencePort.getOrdersFilterByState(state, restaurant.getId(), page, limit),
                orders
        );
    }

    @Test
    void getOrderCorrectly() {
        Long idOrder = 1L;
        User employee = UserData.OWNER;
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Long idClient = UserData.CLIENT.getId();
        int state = OrderState.PENDING;
        Order order = new Order(idOrder, idClient, LocalDate.now(), state, employee.getId(),
                restaurant.getId());
        OrderEntity orderEntity = new OrderEntity(idOrder, idClient, LocalDate.now(), state,restaurant.getId(), employee.getId(),
                null,null);
        when(orderRepository.findById(idOrder))
                .thenReturn(Optional.of(orderEntity));
        when(orderEntityMapper.toOrder(orderEntity))
                .thenReturn(order);
        assertEquals(
                order,
                orderPersistencePort.getOrder(idOrder)
        );
    }

    @Test
    void getOrderButDoesNotExist() {
        Long idOrder = 1L;
        User employee = UserData.OWNER;
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Long idClient = UserData.CLIENT.getId();
        int state = OrderState.PENDING;
        Order order = new Order(idOrder, idClient, LocalDate.now(), state, employee.getId(),
                restaurant.getId());
        OrderEntity orderEntity = new OrderEntity(idOrder, idClient, LocalDate.now(), state,restaurant.getId(), employee.getId(),
                null,null);
        when(orderRepository.findById(idOrder))
                .thenReturn(Optional.empty());

        assertThrows(
                OrderNotFoundException.class,
                ()->orderPersistencePort.getOrder(idOrder)
        );
    }

    @Test
    void updateOrder() {
        Long idOrder = 1L;
        User employee = UserData.OWNER;
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Long idClient = UserData.CLIENT.getId();
        int state = OrderState.READY;
        Order order = new Order(idOrder, idClient, LocalDate.now(), state, employee.getId(),
                restaurant.getId());
        OrderEntity orderEntity = new OrderEntity(idOrder, idClient, LocalDate.now(), OrderState.PENDING,restaurant.getId(), employee.getId(),
                null,null);
        when(orderRepository.findById(idOrder))
                .thenReturn(Optional.of(orderEntity));
        assertDoesNotThrow(
                ()->orderPersistencePort.updateOrder(order)
        );
    }

    @Test
    void saveOrderTicket() {
        Long idOrder = 3L;
        String code = "1242";
        OrderTicket orderTicket = new OrderTicket(idOrder, code);
        OrderTicketEntity orderTicketEntity = new OrderTicketEntity(idOrder, code, null);
        when(orderTicketEntityMapper.toOrderTicketEntity(orderTicket))
                .thenReturn(orderTicketEntity);
        assertDoesNotThrow(
                ()-> orderPersistencePort.saveOrderTicket(orderTicket)
        );
    }

    @Test
    void getOrderTicketWithIdOrderCorrectly() {
        Long idOrder = 3L;
        String code = "1242";
        OrderTicket orderTicket = new OrderTicket(idOrder, code);
        OrderTicketEntity orderTicketEntity = new OrderTicketEntity(idOrder, code, null);
        when(orderTicketRepository.findById(idOrder))
                .thenReturn(Optional.of(orderTicketEntity));
        when(orderTicketEntityMapper.toOrderTicket(orderTicketEntity))
                .thenReturn(orderTicket);
        assertEquals(
                orderTicket,
                orderPersistencePort.getOrderTicketWithIdOrder(idOrder)
        );
    }

    @Test
    void getOrderTicketWithIdOrderButDoesNotExist() {
        Long idOrder = 3L;
        String code = "1242";
        OrderTicket orderTicket = new OrderTicket(idOrder, code);
        OrderTicketEntity orderTicketEntity = new OrderTicketEntity(idOrder, code, null);
        when(orderTicketRepository.findById(idOrder))
                .thenReturn(Optional.empty());
        assertThrows(
                OrderTicketNotFoundException.class,
                ()->orderPersistencePort.getOrderTicketWithIdOrder(idOrder)
        );
    }


    @Test
    void deleteOrderTicketCorrectly() {
        Long idOrder = 3L;
        String code = "1242";
        OrderTicket orderTicket = new OrderTicket(idOrder, code);
        OrderTicketEntity orderTicketEntity = new OrderTicketEntity(idOrder, code, null);
        when(orderTicketRepository.findById(idOrder))
                .thenReturn(Optional.of(orderTicketEntity));
        when(orderTicketEntityMapper.toOrderTicket(orderTicketEntity))
                .thenReturn(orderTicket);
        assertDoesNotThrow(
                ()-> orderPersistencePort.getOrderTicketWithIdOrder(idOrder)
        );
    }

    @Test
    void deleteOrderTicketButDoesNotExist() {
        Long idOrder = 3L;
        String code = "1242";
        OrderTicket orderTicket = new OrderTicket(idOrder, code);
        OrderTicketEntity orderTicketEntity = new OrderTicketEntity(idOrder, code, null);
        when(orderTicketRepository.findById(idOrder))
                .thenReturn(Optional.empty());
        assertThrows(
                OrderTicketNotFoundException.class,
                ()->orderPersistencePort.deleteOrderTicket(idOrder)
        );
    }

    @Test
    void hasActiveOrdersInTheRestaurant() {
        Long idOrder = 1L;
        User employee = UserData.OWNER;
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Long idClient = UserData.CLIENT.getId();
        int state = OrderState.READY;
        List<OrderEntity> orders = List.of(
                new OrderEntity(idOrder, idClient, LocalDate.now(), state,restaurant.getId(), employee.getId(),
                        null,null)
        );
        when(orderRepository.findByIdClient(idClient))
                .thenReturn(orders);
        assertTrue(orderPersistencePort.hasActiveOrdersInTheRestaurant(idClient));
    }

    @Test
    void doesNotHasActiveOrdersInTheRestaurant() {
        Long idOrder = 1L;
        User employee = UserData.OWNER;
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Long idClient = UserData.CLIENT.getId();
        int state = OrderState.CANCELLLED; //Could be cancelled or delivered
        List<OrderEntity> orders = List.of(
                new OrderEntity(idOrder, idClient, LocalDate.now(), state,restaurant.getId(), employee.getId(),
                        null,null)
        );
        when(orderRepository.findByIdClient(idClient))
                .thenReturn(orders);
        assertFalse(orderPersistencePort.hasActiveOrdersInTheRestaurant(idClient));
    }
}