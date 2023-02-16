package com.pragma.userservice.infrastructure.input.rest;

import com.pragma.userservice.application.dto.UserDto;
import com.pragma.userservice.application.dto.UserRegister;
import com.pragma.userservice.application.handler.IUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
public class UserRestController {
    private final IUserHandler personaHandler;

    @PostMapping("/saveOwner")
    public ResponseEntity<Void> savePropietario(@RequestBody UserRegister userRegister){
        personaHandler.saveOwner(userRegister);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getPersona(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(personaHandler.getUser(id));
    }
}
