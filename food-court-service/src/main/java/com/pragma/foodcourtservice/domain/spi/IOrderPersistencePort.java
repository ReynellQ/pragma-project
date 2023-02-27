package com.pragma.foodcourtservice.domain.spi;

import com.pragma.foodcourtservice.domain.model.Order;
import com.pragma.foodcourtservice.domain.model.OrderFoodPlates;

public interface IOrderPersistencePort {
    /**
     * Saves an order in the persistence layer, without the food plates associated to the order.
     * @param order The order to do
     */
    void saveOrder(Order order);
}
