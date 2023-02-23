package com.pragma.messagingservice.domain;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;
import java.math.BigDecimal;
public class Example {
    // Find your Account Sid and Token at twilio.com/console
    @Value("${my.twilio.account-sid}")
    public static String ACCOUNT_SID ;
    @Value("${my.twilio.auth-token}")
    public static final String AUTH_TOKEN = "";
    private static final String SERVICE_SID = "MGfce1a20fa4de1e96d6486e55ecd786ba";
    public static void main(String[] args) {
        String message_text = "algo wiwiwiwi";
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+573157830735"),
                SERVICE_SID,
                message_text)
.create();
        System.out.println(message.getSid());
    }
}
