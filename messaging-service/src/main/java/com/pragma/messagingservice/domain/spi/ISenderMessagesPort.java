package com.pragma.messagingservice.domain.spi;

public interface ISenderMessagesPort {
    void sendMessage(String phone, String body);
}
