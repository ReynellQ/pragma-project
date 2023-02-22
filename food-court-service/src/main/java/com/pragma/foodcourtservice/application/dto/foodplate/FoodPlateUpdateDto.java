package com.pragma.foodcourtservice.application.dto.foodplate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * A DTO to update a food plate.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodPlateUpdateDto {
    private Long id;
    private String description;
    private Long price;
}
