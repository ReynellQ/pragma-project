package com.pragma.foodcourtservice.infrastructure.driven_adapter;

import com.pragma.foodcourtservice.application.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserFeignClientRest {
    @GetMapping("user/{id}")
    public UserDto getUser(@PathVariable Long id);
}
