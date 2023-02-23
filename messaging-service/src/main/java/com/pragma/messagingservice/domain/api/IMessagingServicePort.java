package com.pragma.messagingservice.domain.api;

import com.pragma.messagingservice.domain.model.ReadyOrderMessage;

public interface IMessagingServicePort {
    void sendReadyOrderMessage(ReadyOrderMessage readyOrderMessage);
}
