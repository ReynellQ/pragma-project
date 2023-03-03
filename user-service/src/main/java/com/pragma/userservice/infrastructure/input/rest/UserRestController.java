package com.pragma.userservice.infrastructure.input.rest;

import com.pragma.userservice.application.dto.AuthResponse;
import com.pragma.userservice.application.dto.UserDto;
import com.pragma.userservice.application.dto.UserRegister;
import com.pragma.userservice.application.handler.IUserHandler;
import com.pragma.userservice.infrastructure.configuration.jwt.JwtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@Api(value = "User controller")
@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
@Validated
public class UserRestController {
    private final IUserHandler userHandler;
    private final JwtService jwtService;

    @ApiOperation(value = "Save an owner, only if it's logged an admin")
    @PostMapping("/save/owner")
    @RolesAllowed("ROLE_ADMIN")
    public ResponseEntity<Void> saveOwner(@Valid @RequestBody UserRegister userRegister){
        userHandler.saveOwner(userRegister);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @ApiOperation(value = "Save an employee, only if it's logged an owner")
    @PostMapping("/save/employee")
    @RolesAllowed("ROLE_OWNER")
    public ResponseEntity<Void> saveEmployee(@Valid @RequestBody UserRegister userRegister){
        userHandler.saveEmployee(userRegister);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @ApiOperation(value = "Allows a client to register in the system.")
    @PostMapping("/register")
    public ResponseEntity<Void> saveClient(@Valid @RequestBody UserRegister userRegister){
        userHandler.saveClient(userRegister);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @ApiOperation(value = "Gets an user given their id.", response = UserDto.class)
    @GetMapping("/id/{id}")
    public ResponseEntity<UserDto> getUserById(@Valid @PathVariable(name = "id") Long id){
        return ResponseEntity.ok(userHandler.getUserById(id));
    }

    @ApiOperation(value = "Gets an user given their personal id. (NUIP)", response = UserDto.class)
    @GetMapping("/personalId/{id}")
    public ResponseEntity<UserDto> getUserByPersonalId(@Valid @PathVariable(name = "id") Long id){
        return ResponseEntity.ok(userHandler.getUserByPersonalId(id));
    }

    @ApiOperation(value = "Gets an user given their email.", response = UserDto.class)
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> getUserByPersonalEmail(@Valid @PathVariable(name = "email") String email){
        return ResponseEntity.ok(userHandler.getUserByEmail(email));
    }



}
