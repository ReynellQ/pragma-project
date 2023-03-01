package com.pragma.foodcourtservice.infrastructure.microservices.adapters;

import com.pragma.foodcourtservice.domain.model.ReadyOrderMessage;
import com.pragma.foodcourtservice.domain.spi.IMessagingMicroServiceClientPort;
import com.pragma.foodcourtservice.infrastructure.microservices.feign_client.MessagingFeignClientRest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IMessagingMicroServiceAdapter implements IMessagingMicroServiceClientPort {
    private final MessagingFeignClientRest messagingFeignClientRest;

    /**
     * Send a message with the provided data. Calls the messaging microservice to send the message by Feign Client.
     *
     * @param readyOrderMessage an object with the data to send the message.
     */
    @Override
    public void sendMessage(ReadyOrderMessage readyOrderMessage) {
        messagingFeignClientRest.sendReadyMessage(readyOrderMessage);
    }
}
