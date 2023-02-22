package com.pragma.foodcourtservice.application.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantClientResponse {
    private String name;
    private String urlLogo;
}
