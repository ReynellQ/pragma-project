package com.pragma.foodcourtservice.application.dto.foodplate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO to register a food plate.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodPlateRegisterDto {
    private String name;
    private Long idCategory;
    private String description;
    private Long price;
    private Long idRestaurant;
    private String urlImage;
}
