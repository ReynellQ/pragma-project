package com.pragma.foodcourtservice.application.dto.foodplate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * A DTO to register a food plate.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodPlateRegisterDto {
    @NotNull
    private String name;
    @NotNull
    private Long idCategory;
    @NotNull
    private String description;
    @NotNull
    private Long price;
    @NotNull
    private Long idRestaurant;
    @NotNull
    private String urlImage;
}
