package com.pragma.userservice.application.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * A DTO to register a User, containing the needed data. It's the input of the API for register.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegister {
    @NotNull
    private Long personalId;
    @NotNull
    private String name;
    @NotNull
    private String lastname;
    @NotNull
    private String phone;
    @NotNull
    private String email;
    @NotNull
    private String password;
}
