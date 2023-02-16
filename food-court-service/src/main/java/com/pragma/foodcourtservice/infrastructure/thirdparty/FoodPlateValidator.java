package com.pragma.foodcourtservice.infrastructure.thirdparty;

import com.pragma.foodcourtservice.domain.api.IFoodPlateValidator;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IRestaurantRepository;

import java.util.Optional;

public class FoodPlateValidator implements IFoodPlateValidator {
    private final IRestaurantRepository restaurantRepository;

    public FoodPlateValidator(IRestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    /**
     * Validates if the restaurant exists. Searches it in the repository and returns if finds it or not.
     * @param id the restaurant's id
     * @return true if the restaurant exists, false in other case.
     */
    @Override
    public boolean validatesRestaurant(Long id) {
        Optional<RestaurantEntity> entity = restaurantRepository.findById(id);
        return entity.isPresent();
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
