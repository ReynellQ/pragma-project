package com.pragma.messagingservice.application.handler;

import com.pragma.messagingservice.application.dto.ReadyOrderMessageDto;

public interface IMessagingHandler {
    void sendReadyOrderMessage(ReadyOrderMessageDto readyOrderMessageDto);
}
