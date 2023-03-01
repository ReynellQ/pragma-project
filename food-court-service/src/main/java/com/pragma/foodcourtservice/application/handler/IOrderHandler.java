package com.pragma.foodcourtservice.application.handler;

import com.pragma.foodcourtservice.application.dto.order.DeliverOrderDto;
import com.pragma.foodcourtservice.application.dto.order.OrderIdDto;
import com.pragma.foodcourtservice.application.dto.order.OrderResponseDto;
import com.pragma.foodcourtservice.application.dto.order.OrderWithFoodPlatesDto;

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

    /**
     * Assign the logged employee to an order to change their state.
     * @param orderIdDto a DTO with the id of the order.
     */
    void assignToAnOrder(OrderIdDto orderIdDto);

    /**
     * Notify that the order with the provided ID is ready and the client can claim
     * @param orderIdDto a DTO with the id of the order.
     */
    void notifyTheOrderIsReady(OrderIdDto orderIdDto);

    /**
     * Deliver an order with the id provided and verifies the pin.
     * @param deliverOrderDto a DTO with the order's id and the pin code of the order.
     */
    void deliverAnOrder(DeliverOrderDto deliverOrderDto);
}
