package com.pragma.foodcourtservice.domain.api;

import com.pragma.foodcourtservice.domain.model.Order;
import com.pragma.foodcourtservice.domain.model.OrderFoodPlates;

import java.util.List;

/**
 * Interface that defines the API port of the order service and exposes the methods to the API.
 */
public interface IOrderServicePort {
    /**
     * Saves an order with the associated food plates and their quantities.
     * @param order The order to do
     * @param foodPlates The food plates
     */
    void saveOrder(Order order, List<OrderFoodPlates> foodPlates);
}
