package com.pragma.userservice.infrastructure.input.rest;

import com.pragma.userservice.application.dto.AuthResponse;
import com.pragma.userservice.application.dto.UserLoginDto;
import com.pragma.userservice.application.handler.IUserHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "Login controller")
@RestController
@RequestMapping("/auth/")
@RequiredArgsConstructor
@Validated
public class LoginRestController {
    private final IUserHandler userHandler;
    @ApiOperation(value = "Allows the user to login.", response = AuthResponse.class)
    @PostMapping("/")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody UserLoginDto login){
        AuthResponse response = userHandler.authUser(login);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization",
                response.getToken());
        return ResponseEntity.ok().headers(responseHeaders).body(response);
    }
}
