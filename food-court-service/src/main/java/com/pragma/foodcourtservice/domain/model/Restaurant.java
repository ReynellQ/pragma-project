package com.pragma.foodcourtservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * POJO class for Restaurant.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Restaurant {
    private Long id;
    private String name;
    private String address;
    private Long idOwner;
    private String phone;
    private String urlLogo;
    private Long nit;
}
