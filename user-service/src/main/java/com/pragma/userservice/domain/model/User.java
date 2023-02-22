package com.pragma.userservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * POJO class for User. Represents a User in the Model layer.
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private Long id;
    private Long personalId;
    private String name;
    private String lastname;
    private String phone;
    private String email;
    private String password;
    private Integer idRole;
}
