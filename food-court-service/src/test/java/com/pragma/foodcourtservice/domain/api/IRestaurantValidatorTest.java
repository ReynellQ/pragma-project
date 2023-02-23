package com.pragma.foodcourtservice.domain.api;

import com.pragma.foodcourtservice.UserData;
import com.pragma.foodcourtservice.infrastructure.driven_adapter.RestaurantValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class IRestaurantValidatorTest {
    IRestaurantValidator restaurantValidator;

    @BeforeEach
    void setUp(){
        restaurantValidator = new RestaurantValidator();
    }

    /**
     * Validates if the user provided is an owner.
     * An owner is valid if it's role is the role of an Owner (currently 2).
     */
    @Test
    void validateOwner() {
        assertTrue(restaurantValidator.validateOwner(UserData.OWNER));
        assertFalse(restaurantValidator.validateOwner(UserData.ADMIN));
    }

    /**
     * A phone is valid if it doesn't exceed 13 characters, is numeric or '+' + all numeric.
     * Assert the valid and invalid phones.
     */
    @Test
    void validatePhone() {
        String[]phones = {
                "123", "1234", "+1234", "+573157830735"
        };
        String[] invalidPhones = {
                "xd", "data", "lalal", "+2313*", "++24252", "1234567890124232"
        };
        //Assert that all the phones are valid phones.
        Arrays.asList(phones).forEach( (phone) -> assertTrue(restaurantValidator.validatePhone(phone)));
        //Assert that all the invalid phones are, effectively, invalid phones.
        Arrays.asList(invalidPhones).forEach( (phone) -> assertFalse(restaurantValidator.validatePhone(phone)));
    }

    /**
     * Validates if the submitted names are valid or not.
     * A name is valid if isn't all numeric.
     */
    @Test
    void validateName() {
        String[]names = {
                "restaurante maestro", "8va maravilla", "123 listos fuera", "restaurante 1"
        };
        String[] invalidNames = {
                "6", "2131244"
        };
        //Assert that all the phones are valid phones.
        Arrays.asList(names).forEach( (name) -> assertTrue(restaurantValidator.validateName(name)));
        //Assert that all the invalid phones are, effectively, invalid phones.
        Arrays.asList(invalidNames).forEach( (name) -> assertFalse(restaurantValidator.validateName(name)));
    }
}