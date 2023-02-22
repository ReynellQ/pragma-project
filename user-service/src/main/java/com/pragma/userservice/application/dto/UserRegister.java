package com.pragma.userservice.application.dto;

import lombok.*;

/**
 * A DTO to register a User, containing the needed data. It's the input of the API for register.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegister {
    private Long personalId;
    private String name;
    private String lastname;
    private String phone;
    private String email;
    private String password;
}
