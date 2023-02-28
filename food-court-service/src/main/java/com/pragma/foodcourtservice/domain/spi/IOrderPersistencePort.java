package com.pragma.foodcourtservice.domain.spi;

import com.pragma.foodcourtservice.domain.model.Order;
import com.pragma.foodcourtservice.domain.model.OrderFoodPlates;

import java.util.List;

public interface IOrderPersistencePort {
    /**
     * Saves and retrieves an order in the persistence layer, with the food plates associated to the order.
     * @param order The order to save.
     * @param orderFoodPlates The food plates ordered and their quantities.
     */
    void saveOrder(Order order, List<OrderFoodPlates> orderFoodPlates);
}
