package com.pragma.foodcourtservice.domain.api;

import com.pragma.foodcourtservice.domain.model.User;

/**
 * Interface that has the validations of a restaurant.
 */
public interface IRestaurantValidator {
    /**
     * Validates if the user (it's assumed that was found in the user microservice) is an owner.
     * @param user the restaurant's owner
     * @return true if role's user is "owner", false in other case.
     */

    boolean validateOwner(User user);

    /**
     * Checks if the string phone is a valid phone.
     * @param phone
     * @return true if is valid, false in the other case.
     */
    boolean validatePhone(String phone);
    /**
     * Checks if the string name is a valid name.
     * @param name
     * @return true if is valid, false in the other case.
     */
    boolean validateName(String name);
}
