package com.pragma.foodcourtservice.application.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * A DTO for User. It's the output of the API for Food Court.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantCreateDto {
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String phone;
    @NotNull
    private String urlLogo;
    @NotNull
    private Long nit;
}
