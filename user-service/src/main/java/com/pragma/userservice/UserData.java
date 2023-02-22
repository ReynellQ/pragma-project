package com.pragma.userservice;

import com.pragma.userservice.domain.model.User;

public class UserData {
    public static final User USER_001 = new User(1l, 1l, "Lalo", "Salamanca", "123",
            "lalo@gmail.com", "password", 2);
    public static final User USER_002 = new User(2l, 2l, "Tuco", "Salamanca", "123",
            "tuco@gmail.com", "password", 2);
    public static final User NON_INSERTED_USER_001 = new User(3l, 3L, "Walter", "White", "123",
            "walterwhite@gmail.com", "password", null);
    public static final User NON_INSERTED_USER_002 = new User(4l, 4l, "Hank", "Schredder", "123",
            "walterwhite@gmail.com", "password", null);
    public static final User NON_INSERTED_USER_003 = new User(1l, 1l, "Danny", "Phantom", "123",
            "dannyphantom@gmail.com", "password", null);

    public static final User NON_INSERTED_USER_004 = new User(5l, 5l, "Danny", "Phantom", "123",
            "lalo@gmail.com", "password", null);
    public static final User NON_INSERTED_USER_005 = new User(10l, 10l, "Danny", "Phantom", "+123",
            "incorrectemail", "password", null);
    public static final User NON_INSERTED_USER_006 = new User(10l, 10l, "Danny", "Phantom", "esto es un telefono",
            "correctemail@gmail.com", "password", null);
    public static final User NON_INSERTED_USER_007 = new User(10l, 10l, "Danny", "Phantom", "+57315783071243",
            "what@is.this-.4", "password", null);
}
