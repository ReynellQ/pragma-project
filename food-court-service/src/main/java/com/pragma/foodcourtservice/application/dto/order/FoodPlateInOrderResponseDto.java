package com.pragma.foodcourtservice.application.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodPlateInOrderResponseDto {
    private String name;
    private Long quantity;
}
