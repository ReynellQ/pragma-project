package com.pragma.foodcourtservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * POJO class for a food plate.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodPlate {
    private Long id;
    private String name;
    private Long idCategory;
    private Category category;
    private String description;
    private Long price;
    private Long idRestaurant;
    private String urlImage;
    private Boolean active;
}
