package com.pragma.foodcourtservice.application.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantEmployeeDto {
    private Long personalId;
    private String name;
    private String lastname;
    private String phone;
    private String email;
    private String password;
    private Long idRestaurant;
}
