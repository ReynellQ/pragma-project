package com.pragma.foodcourtservice.infrastructure.driven_adapter;

import com.pragma.foodcourtservice.application.dto.UserDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserFeignClientRest {
    @GetMapping("user/id/{id}")
    UserDto getUserByPersonalId(@PathVariable Long id);

    @GetMapping("user/email/{email}")
    UserDto getUserByEmail(@PathVariable String email);
}


