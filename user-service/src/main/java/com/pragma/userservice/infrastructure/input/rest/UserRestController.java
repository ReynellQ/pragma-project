package com.pragma.userservice.infrastructure.input.rest;

import com.pragma.userservice.application.dto.UserDto;
import com.pragma.userservice.application.dto.UserRegister;
import com.pragma.userservice.application.handler.IUserHandler;
import com.pragma.userservice.infrastructure.configuration.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
@Validated
public class UserRestController {
    private final IUserHandler userHandler;
    private final JwtService jwtService;

    @PostMapping("/save/owner")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Void> saveOwner(@Valid @RequestBody UserRegister userRegister){
        userHandler.saveOwner(userRegister);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @PostMapping("/save/employee")
    @RolesAllowed("ROLE_OWNER")
    public ResponseEntity<Void> saveEmployee(@Valid @RequestBody UserRegister userRegister){
        userHandler.saveEmployee(userRegister);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/register")
    public ResponseEntity<Void> saveClient(@Valid @RequestBody UserRegister userRegister){
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
