package com.pragma.foodcourtservice.infrastructure.driven_adapter;

import com.pragma.foodcourtservice.domain.api.IFoodPlateValidator;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FoodPlateValidator implements IFoodPlateValidator {


    /**
     * Validates the price of the food plate. The price has to be positive.
     * @param price the price of the food plate
     * @return true if the price is valid, false in other case
     */
    @Override
    public boolean validatesPrice(Long price) {
        return price != null && price > 0;
    }
}
