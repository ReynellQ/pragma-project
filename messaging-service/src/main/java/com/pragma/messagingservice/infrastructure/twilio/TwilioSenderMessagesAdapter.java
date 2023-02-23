package com.pragma.messagingservice.infrastructure.twilio;

import com.pragma.messagingservice.domain.spi.ISenderMessagesPort;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;

public class TwilioSenderMessagesAdapter implements ISenderMessagesPort {

    private String accountSid;

    private String authToken;
    private static final String SERVICE_SID = "MGfce1a20fa4de1e96d6486e55ecd786ba";

    @Value(value = "${my.twilio.account-sid}")
    public void setAccountSid(String accountSid){
        this.accountSid = accountSid;
    }

    @Value("${my.twilio.auth-token}")
    public void setAuthToken(String authToken){
        this.authToken = authToken;
    }
    @Override
    public void sendMessage(String phone, String body) {
        Twilio.init(accountSid, authToken);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber(phone),
                        SERVICE_SID,
                        body)
                .create();
    }
}
