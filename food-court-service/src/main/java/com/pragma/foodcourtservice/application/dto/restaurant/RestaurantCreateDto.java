package com.pragma.foodcourtservice.application.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO for User. It's the output of the API for Food Court.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantCreateDto {
    private String name;
    private String address;
    private String phone;
    private String urlLogo;
    private Long nit;
}
