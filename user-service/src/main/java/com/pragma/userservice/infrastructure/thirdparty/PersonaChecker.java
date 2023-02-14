package com.pragma.userservice.infrastructure.thirdparty;

import com.pragma.userservice.domain.api.IPersonaChecker;
import java.util.regex.*;
import java.util.*;
public class PersonaChecker implements IPersonaChecker {
    @Override
    public boolean emailChecker(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public boolean phoneChecker(String phone) {
        if(phone.charAt(0) == '+')
            return phoneChecker(phone.substring(1));
        return allNumeric(phone);
    }
    private boolean allNumeric(String str){
        for(char c : str.toCharArray())
            if(!Character.isDigit(c))
                return false;
        return true;
    }
}
