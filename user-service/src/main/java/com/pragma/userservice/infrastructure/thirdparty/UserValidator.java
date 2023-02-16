package com.pragma.userservice.infrastructure.thirdparty;

import com.pragma.userservice.domain.api.IUserValidator;
import org.yaml.snakeyaml.util.ArrayUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.regex.*;

/**
 * Implementation of the interface that validate the data of a User.
 */
public class UserValidator implements IUserValidator {
    /**
     * Checks if the string email is a valid email. Uses a regex that follows the RFC 5322 standard.
     * @param email
     * @return true if is valid, false in the other case.
     */
    @Override
    public boolean emailChecker(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    /**
     * Checks if the string phone is a valid email.
     * The criteria are:
     * - No more than 13 characters.
     * - If the first character if a '+', the remaining characters have to be all numeric. In other case all the
     * characters have to be numeric.
     * - In other case, the phone isn't a valida phone.
     * @param phone
     * @return true if is valid, false in the other case.
     */
    @Override
    public boolean phoneChecker(String phone) {
        if(phone.length() > 13){
            return false;
        }
        if(phone.charAt(0) == '+'){
            return phoneChecker(phone.substring(1));
        }
        return allNumeric(phone);
    }

    /**
     * Checks if all characters of a String are numeric.
     * @param str
     * @return
     */
    private boolean allNumeric(String str){
        for(char c : str.toCharArray()){
            if(!Character.isDigit(c)){
                return false;
            }
        }
        return true;
    }
}
