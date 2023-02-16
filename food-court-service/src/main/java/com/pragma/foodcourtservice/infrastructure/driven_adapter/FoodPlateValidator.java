package com.pragma.foodcourtservice.infrastructure.driven_adapter;

import com.pragma.foodcourtservice.domain.api.IFoodPlateValidator;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IRestaurantRepository;

public class FoodPlateValidator implements IFoodPlateValidator {
    private final IRestaurantRepository restaurantRepository;

    public FoodPlateValidator(IRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Validates the price of the food plate. The price has to be positive.
     * @param price the price of the food plate
     * @return true if the price is valid, false in other case
     */
    @Override
    public boolean validatesPrice(Long price) {
        return price > 0;
    }
}
