package com.pragma.foodcourtservice.domain.api;

import com.pragma.foodcourtservice.domain.model.Order;
import com.pragma.foodcourtservice.domain.model.OrderFoodPlates;
import com.pragma.foodcourtservice.domain.model.OrderWithFoodPlates;

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

    /**
     * Gets the orders of the restaurant filtered by state. The restaurant is the working place of the logged employee.
     * @param state the state provided to filter.
     * @param page the page to return.
     * @param limit the amount of registers per page.
     * @return a list with the Orders.
     */
    List<OrderWithFoodPlates> getOrdersFilterByState(Integer state, Integer page, Integer limit);

    /**
     * Assign to an order to change their state.
     * @param idOrder the id of the order.
     */
    void assignToAnOrder(Long idOrder);

    /**
     * Notify that the order with the provided ID is ready and the client can claim
     * @param idOrder the id of the order.
     */
    void notifyTheOrderIsReady(Long idOrder);

    /**
     * Deliver an order with the id provided and verifies the pin.
     * @param idOrder the id of the order.
     * @param pin the pin to deliver the order.
     */
    void deliverAnOrder(Long idOrder, String pin);
}
