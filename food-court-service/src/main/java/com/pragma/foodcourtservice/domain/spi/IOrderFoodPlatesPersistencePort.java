package com.pragma.foodcourtservice.domain.spi;

import com.pragma.foodcourtservice.domain.model.Order;
import com.pragma.foodcourtservice.domain.model.OrderFoodPlates;

import java.util.List;

public interface IOrderFoodPlatesPersistencePort {
    /**
     * Saves the associated food plates of an order and their quantities in the persistence layer.
     * @param orderFoodPlates The information of the food plates of the order.
     */
    void savesFoodPlatesOrder(List<OrderFoodPlates> orderFoodPlates);
}
