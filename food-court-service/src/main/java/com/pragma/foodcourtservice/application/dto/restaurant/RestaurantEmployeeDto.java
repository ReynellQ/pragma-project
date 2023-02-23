package com.pragma.foodcourtservice.application.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantEmployeeDto {
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
    @NotNull
    private Long idRestaurant;
}
