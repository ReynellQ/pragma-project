package com.pragma.foodcourtservice;


import com.pragma.foodcourtservice.domain.model.User;

public class UserData {
    public static final User OWNER = new User(1l, 1l, "Lalo", "Salamanca", "123",
            "lalo@gmail.com", "password", 2);
    public static final User ADMIN = new User(2l, 2l, "Tuco", "Salamanca", "123",
            "tuco@gmail.com", "password", 1);
    public static final User NON_INSERTED_USER_001 = new User(3l, 3l, "Walter", "White", "123",
            "walterwhite@gmail.com", "password", null);
    public static final User USER_WITH_INCORRECT_EMAIL = new User(10l, 10l, "Danny", "Phantom", "+123",
            "incorrectemail", "password", null);
}
