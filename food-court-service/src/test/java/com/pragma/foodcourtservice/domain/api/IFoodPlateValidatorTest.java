package com.pragma.foodcourtservice.domain.api;

import com.pragma.foodcourtservice.infrastructure.driven_adapter.FoodPlateValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IFoodPlateValidatorTest {
    IFoodPlateValidator foodPlateValidator;
    @BeforeEach
    void setUp(){
        foodPlateValidator = new FoodPlateValidator();
    }
    @Test
    void validatesPrice() {
        Long goodPrice = 1l;
        Long badPrice = -1l;
        Long nullPrice = null;
        assertAll(
                ()-> assertTrue(foodPlateValidator.validatesPrice(goodPrice)),
                ()-> assertFalse(foodPlateValidator.validatesPrice(badPrice)),
                ()-> assertFalse(foodPlateValidator.validatesPrice(nullPrice))
        );
    }

}