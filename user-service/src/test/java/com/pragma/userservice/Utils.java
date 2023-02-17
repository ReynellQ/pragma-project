package com.pragma.userservice;

import com.pragma.userservice.domain.model.User;

public class Utils {
    /**
     * Method using to clone a User object and not include this method in the model because isn't necessary. Use it
     * when you want to use an object from the data and modifying it, in order to don't alter the original object.
     * @param u
     * @return
     */
    public static User cloneUser(User u){
        return new User(u.getId(), u.getName(), u.getLastname(), u.getPhone(), u.getEmail(), u.getPassword(),
                u.getIdRole());
    }
}
