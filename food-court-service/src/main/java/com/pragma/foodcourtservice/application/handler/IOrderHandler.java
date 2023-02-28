package com.pragma.foodcourtservice.application.handler;

import com.pragma.foodcourtservice.application.dto.order.OrderWithFoodPlatesDto;

public interface IOrderHandler {
    /**
     * Saves an order with the associated food plates and their quantities in the application.
     * @param orderWithFoodPlatesDto the info of the order and the food plates associated.
     */
    void saveOrder(OrderWithFoodPlatesDto orderWithFoodPlatesDto);
}
