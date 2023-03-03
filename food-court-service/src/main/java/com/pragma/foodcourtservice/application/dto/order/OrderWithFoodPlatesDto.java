package com.pragma.foodcourtservice.application.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderWithFoodPlatesDto {
    @NotNull
    private Long idRestaurant;
    @NotNull
    private List<FoodPlateOrderDto> foodPlateOrderDtoList;
}
