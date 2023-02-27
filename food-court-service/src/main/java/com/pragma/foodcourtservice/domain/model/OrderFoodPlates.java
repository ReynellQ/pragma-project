package com.pragma.foodcourtservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderFoodPlates {
    private Long idOrder;
    private Long idFoodPlate;
    private Long quantity;
}
