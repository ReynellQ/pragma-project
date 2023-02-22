package com.pragma.userservice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for User. It's the output of the API for User.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Long id;
    private Long personalId;
    private String name;
    private String lastname;
    private String phone;
    private String email;
    private String password;
    private RoleDto role;
}
