package com.pragma.foodcourtservice.domain.spi;

import com.pragma.foodcourtservice.FoodPlateData;
import com.pragma.foodcourtservice.RestaurantData;
import com.pragma.foodcourtservice.UserData;
import com.pragma.foodcourtservice.domain.model.*;
import com.pragma.foodcourtservice.infrastructure.output.jpa.adapter.OrderJpaAdapter;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.OrderEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.OrderFoodPlatesEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.pk.OrderFoodPlatesEntityID;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.OrderEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IOrderFoodPlatesRepository;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IOrderRepository;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IOrderTicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IOrderPersistencePortTest {
    IOrderRepository orderRepository;
    IOrderTicketRepository orderTicketRepository;
    IOrderFoodPlatesRepository orderFoodPlatesRepository;
    OrderEntityMapper orderEntityMapper;

    IOrderPersistencePort orderPersistencePort;
    @BeforeEach
    void setUp(){
        orderRepository = mock(IOrderRepository.class);
        orderFoodPlatesRepository = mock(IOrderFoodPlatesRepository.class);
        orderEntityMapper = mock(OrderEntityMapper.class);
        orderPersistencePort = new OrderJpaAdapter(orderRepository,orderTicketRepository, orderFoodPlatesRepository,
                orderEntityMapper);

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
    }

    @Test
    void getOrder() {
    }

    @Test
    void updateOrder() {
    }
}