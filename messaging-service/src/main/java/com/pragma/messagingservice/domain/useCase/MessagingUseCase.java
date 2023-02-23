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
        String body = "Your order is ready.\nYour pin is " + readyOrderMessage.getPin();
        senderMessagesPort.sendMessage(readyOrderMessage.getPhone(), body);
    }
}
