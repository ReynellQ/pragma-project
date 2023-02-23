package com.pragma.messagingservice.application.handler;

import com.pragma.messagingservice.application.dto.ReadyOrderMessageDto;
import com.pragma.messagingservice.application.mapper.MessagesDtoMapper;
import com.pragma.messagingservice.domain.api.IMessagingServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessagingHandler implements IMessagingHandler{
    private final IMessagingServicePort messagingServicePort;
    private final MessagesDtoMapper messagesDtoMapper;
    @Override
    public void sendReadyOrderMessage(ReadyOrderMessageDto readyOrderMessageDto) {
        messagingServicePort
                .sendReadyOrderMessage(messagesDtoMapper.toMessage(readyOrderMessageDto));
    }
}
