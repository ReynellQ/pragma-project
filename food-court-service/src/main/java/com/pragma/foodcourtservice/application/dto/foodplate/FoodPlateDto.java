package com.pragma.foodcourtservice.application.dto.foodplate;

import com.pragma.foodcourtservice.domain.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FoodPlateDto {
    private String category;
    private String name;
    private String description;
    private Long price;
    private String urlImage;
    private Boolean active;
}
