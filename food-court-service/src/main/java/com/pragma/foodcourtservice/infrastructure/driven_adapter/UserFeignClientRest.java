package com.pragma.foodcourtservice.infrastructure.driven_adapter;

import com.pragma.foodcourtservice.application.dto.users.UserDto;
import com.pragma.foodcourtservice.domain.model.User;
import com.pragma.foodcourtservice.infrastructure.configuration.feign.FeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service",
            configuration = {FeignClientConfiguration.class})
public interface UserFeignClientRest {
    @GetMapping("user/id/{id}")
    UserDto getUserByPersonalId(@PathVariable Long id);

    @GetMapping("user/email/{email}")
    UserDto getUserByEmail(@PathVariable String email);

    @PostMapping("user/save/employee")
    void saveAnEmployee(@RequestBody User user);
}


