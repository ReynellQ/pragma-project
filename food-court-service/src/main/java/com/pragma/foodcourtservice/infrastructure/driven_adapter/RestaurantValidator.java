package com.pragma.foodcourtservice.infrastructure.driven_adapter;

import com.pragma.foodcourtservice.domain.api.IRestaurantValidator;
import com.pragma.foodcourtservice.domain.model.ROLES;
import com.pragma.foodcourtservice.domain.model.User;

/**
 * Class that has the validations of a restaurant.
 */
public class RestaurantValidator implements IRestaurantValidator {
    /**
     * Validates if the user (it's assumed that was found in the user microservice) is an owner.
     * Currently, the role's id of the owner role is 2.
     * @param owner the restaurant's owner
     * @return true if role's user is "owner", false in other case.
     */
    @Override
    public boolean validateOwner(User owner) {
        return owner.getIdRole() == ROLES.OWNER;
    }
    /**
     * Checks if the string phone is a valid phone.
     * The criteria are:
     * - No more than 13 characters.
     * - If the first character if a '+', the remaining characters have to be all numeric. In other case all the
     * characters have to be numeric.
     * - In other case, the phone isn't a valida phone.
     * @param phone
     * @return true if is valid, false in the other case.
     */
    @Override
    public boolean validatePhone(String phone) {
        if(phone.length() > 13){
            return false;
        }
        if(phone.charAt(0) == '+')
            return allNumeric(phone.substring(1));
        return allNumeric(phone);
    }


    /**
     * Checks if the string name is a valid name.
     * The criteria are:
     * - The name is not composed by only numeric characters.
     * @param name the name of the restaurant
     * @return true if is valid, false in other case.
     */
    @Override
    public boolean validateName(String name) {
        return !allNumeric(name);
    }
    /**
     * Checks if all characters of a String are numeric.
     * @param str
     * @return true if all characters are digits, false in other case.
     */
    private boolean allNumeric(String str){
        for(char c : str.toCharArray())
            if(!Character.isDigit(c))
                return false;
        return true;
    }
}
