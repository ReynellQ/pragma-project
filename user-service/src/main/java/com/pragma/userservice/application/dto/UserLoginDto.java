package com.pragma.userservice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for the data to log in a user. It's the input for the API to log in.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLoginDto {
    private String email;
    private String password;
}
