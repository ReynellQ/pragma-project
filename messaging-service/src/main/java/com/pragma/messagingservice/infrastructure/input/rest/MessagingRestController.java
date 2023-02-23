package com.pragma.messagingservice.infrastructure.input.rest;

import com.pragma.messagingservice.application.dto.ReadyOrderMessageDto;
import com.pragma.messagingservice.application.handler.IMessagingHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/send_message/")
public class MessagingRestController {
    private final IMessagingHandler messagingHandler;
    @PostMapping("/ready_order")
    public ResponseEntity<Void> sendReadyOrderMessage(@RequestBody ReadyOrderMessageDto readyOrderMessageDto){
        messagingHandler.sendReadyOrderMessage(readyOrderMessageDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
