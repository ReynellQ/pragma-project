package com.pragma.foodcourtservice;

import com.pragma.foodcourtservice.domain.api.IRestaurantValidator;
import com.pragma.foodcourtservice.domain.model.User;
import com.pragma.foodcourtservice.infrastructure.driven_adapter.RestaurantValidator;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;


/**
 * Not implemented.
 */
@SpringBootTest
public class IRestaurantValidatorTests {
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
    void validateOwner(){
        User owner = UserData.OWNER;
        User notOwner = UserData.ADMIN;
        assertTrue(restaurantValidator.validateOwner(owner));
        assertTrue(restaurantValidator.validateOwner(notOwner));
    }

    /**
     * A phone is valid if it doesn't exceed 13 characters, is numeric or '+' + all numeric.
     * Assert the valid and invalid phones.
     */
    @Test
    void validatePhones(){
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
    void validateNames(){
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
