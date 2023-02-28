package com.pragma.foodcourtservice.application.handler;

import com.pragma.foodcourtservice.application.dto.order.OrderResponseDto;
import com.pragma.foodcourtservice.application.dto.order.OrderWithFoodPlatesDto;
import com.pragma.foodcourtservice.domain.model.Order;

import java.util.List;

public interface IOrderHandler {
    /**
     * Saves an order with the associated food plates and their quantities in the application.
     * @param orderWithFoodPlatesDto the info of the order and the food plates associated.
     */
    void saveOrder(OrderWithFoodPlatesDto orderWithFoodPlatesDto);

    /**
     * Gets the orders of the restaurant filtered by state in the application.
     * @param state the state provided to filter.
     * @param page the page to return.
     * @param limit the amount of registers per page.
     * @return a list with the Orders.
     */
    List<OrderResponseDto> getOrdersFilterByState(Integer state, Integer page, Integer limit);
}
