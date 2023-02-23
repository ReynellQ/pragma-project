package com.pragma.messagingservice.infrastructure.configuration;

import com.pragma.messagingservice.domain.api.IMessagingServicePort;
import com.pragma.messagingservice.domain.spi.ISenderMessagesPort;
import com.pragma.messagingservice.domain.useCase.MessagingUseCase;
import com.pragma.messagingservice.infrastructure.twilio.TwilioSenderMessagesAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ISenderMessagesPort senderMessagesPort(){
        return new TwilioSenderMessagesAdapter();
    }
    @Bean
    public IMessagingServicePort messagingServicePort(){
        return new MessagingUseCase(senderMessagesPort());
    }
}
