package com.pragma.foodcourtservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderWithFoodPlates {
    private Long id;
    private Long idClient;
    private LocalDate date;
    private Integer state;
    private Long idChef;
    private Long idRestaurant;
    private List<FoodPlateInOrder> foodPlates;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FoodPlateInOrder{
        private String name;
        private Long quantity;
    }
}
