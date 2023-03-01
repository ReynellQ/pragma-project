package com.pragma.foodcourtservice.infrastructure.microservices.feign_client;

import com.pragma.foodcourtservice.domain.model.ReadyOrderMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "messaging-service")
public interface MessagingFeignClientRest {
    @GetMapping("send_message/ready_order")
    void sendReadyMessage(@RequestBody ReadyOrderMessage readyOrderMessageDto);

}
