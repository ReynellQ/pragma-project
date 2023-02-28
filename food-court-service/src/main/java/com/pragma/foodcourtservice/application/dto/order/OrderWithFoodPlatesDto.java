package com.pragma.foodcourtservice.application.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderWithFoodPlatesDto {
    private Long idRestaurant;
    private List<FoodPlateOrderDto> foodPlateOrderDtoList;
}
