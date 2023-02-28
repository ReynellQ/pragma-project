package com.pragma.foodcourtservice.application.dto.order;

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
public class OrderResponseDto {
    private Long id;
    private Long idClient;
    private LocalDate date;
    private Long idChef;
    private List<FoodPlateInOrderResponseDto> foodPlates;
}
