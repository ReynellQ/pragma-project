package com.pragma.foodcourtservice.domain.spi;

import com.pragma.foodcourtservice.domain.model.ReadyOrderMessage;

public interface IMessagingMicroServiceClientPort {
    /**
     * Send a message with the provided data.
     * @param readyOrderMessage an object with the data to send the message.
     */
    void sendMessage(ReadyOrderMessage readyOrderMessage);
}
