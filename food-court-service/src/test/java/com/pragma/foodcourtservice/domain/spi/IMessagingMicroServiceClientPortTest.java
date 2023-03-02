package com.pragma.foodcourtservice.domain.spi;

import com.pragma.foodcourtservice.domain.model.ReadyOrderMessage;
import com.pragma.foodcourtservice.infrastructure.microservices.adapters.IMessagingMicroServiceAdapter;
import com.pragma.foodcourtservice.infrastructure.microservices.feign_client.MessagingFeignClientRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class IMessagingMicroServiceClientPortTest {
    MessagingFeignClientRest messagingFeignClientRest;

    IMessagingMicroServiceClientPort messagingMicroServiceClientPort;
    @BeforeEach
    void setUp(){
        messagingFeignClientRest = mock(MessagingFeignClientRest.class);
        messagingMicroServiceClientPort = new IMessagingMicroServiceAdapter(messagingFeignClientRest);
    }
    @Test
    void sendMessage() {
        ReadyOrderMessage readyOrderMessage = new ReadyOrderMessage(1L, "Restaurante", "+573157830735",
                "1234");
        assertDoesNotThrow(
                ()-> messagingMicroServiceClientPort.sendMessage(readyOrderMessage)
        );
    }
}