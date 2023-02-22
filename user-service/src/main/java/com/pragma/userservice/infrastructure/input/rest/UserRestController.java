package com.pragma.userservice.infrastructure.input.rest;

import com.pragma.userservice.application.dto.UserDto;
import com.pragma.userservice.application.dto.UserRegister;
import com.pragma.userservice.application.handler.IUserHandler;
import com.pragma.userservice.infrastructure.configuration.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserHandler userHandler;
    private final JwtService jwtService;

    @PostMapping("/save/owner")
    public ResponseEntity<Void> saveOwner(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
                                          @RequestBody UserRegister userRegister){
        jwt = jwt.substring(7);
        if(!jwtService.extractRole(jwt).equals("ADMIN")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        userHandler.saveOwner(userRegister);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @PostMapping("/save/employee")
    public ResponseEntity<Void> saveEmployee(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
                                             @RequestBody UserRegister userRegister){
        jwt = jwt.substring(7);
        if(!jwtService.extractRole(jwt).equals("OWNER")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        userHandler.saveEmployee(userRegister);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/register")
    public ResponseEntity<Void> saveEmployee(@RequestBody UserRegister userRegister){
        userHandler.saveClient(userRegister);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserDto> getUserByPersonalId(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(userHandler.getUserByPersonalId(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByPersonalEmail(@PathVariable(name = "email") String email){
        return ResponseEntity.ok(userHandler.getUserByEmail(email));
    }



}
