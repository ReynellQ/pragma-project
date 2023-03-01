package com.pragma.foodcourtservice.domain.api;

import com.pragma.foodcourtservice.domain.model.Order;

public interface IOrderNotifierPort {
    /**
     * Sends a message to the client of the order.
     * @param order the data of the order.
     */
    void sendMessage(Order order);

    /**
     * Generates a code with 4 digits randomly.
     * @return a String representing a code with 4 digits.
     */
    String generateRandomCode();
}
