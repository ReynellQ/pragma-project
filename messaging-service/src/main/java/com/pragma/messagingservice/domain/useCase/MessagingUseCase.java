package com.pragma.messagingservice.domain.useCase;

import com.pragma.messagingservice.domain.api.IMessagingServicePort;
import com.pragma.messagingservice.domain.model.ReadyOrderMessage;
import com.pragma.messagingservice.domain.spi.ISenderMessagesPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MessagingUseCase implements IMessagingServicePort {
    private final ISenderMessagesPort senderMessagesPort;

    @Override
    public void sendReadyOrderMessage(ReadyOrderMessage readyOrderMessage) {
        String body = "Your order #" +
                readyOrderMessage.getIdOrder() +
                " is ready from the restaurant " +
                readyOrderMessage.getRestaurant() +
                ".\nYour pin is " + readyOrderMessage.getPin();
        senderMessagesPort.sendMessage(readyOrderMessage.getPhone(), body);
    }
}
