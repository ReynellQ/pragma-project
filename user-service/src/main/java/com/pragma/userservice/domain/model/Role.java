package com.pragma.userservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * POJO class for Role. Represents a Role in the Model layer.
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {
    private Integer id;
    private String name;
    private String description;
}
