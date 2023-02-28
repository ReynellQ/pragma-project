package com.pragma.foodcourtservice.domain.spi;

import com.pragma.foodcourtservice.domain.model.Order;
import com.pragma.foodcourtservice.domain.model.OrderFoodPlates;
import com.pragma.foodcourtservice.domain.model.OrderWithFoodPlates;

import java.util.List;

public interface IOrderPersistencePort {
    /**
     * Saves and retrieves an order in the persistence layer, with the food plates associated to the order.
     * @param order The order to save.
     * @param orderFoodPlates The food plates ordered and their quantities.
     */
    void saveOrder(Order order, List<OrderFoodPlates> orderFoodPlates);

    /**
     * Gets the orders of the provided restaurant filtered by state from the persistence layer.
     * @param state the state to filter.
     * @param idRestaurant the id of the restaurant to search.
     * @param page the page to return.
     * @param limit the amount of registers per page.
     * @return a list with the orders from the provided restaurant and state.
     */
    List<OrderWithFoodPlates> getOrdersFilterByState(Integer state, Long idRestaurant, Integer page, Integer limit);

}
