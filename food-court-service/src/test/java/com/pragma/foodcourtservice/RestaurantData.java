package com.pragma.foodcourtservice;

import com.pragma.foodcourtservice.domain.model.Restaurant;
import com.pragma.foodcourtservice.domain.model.User;

public class RestaurantData {
    public static final Restaurant RESTAURANT_001 = new Restaurant(1l, "Restaurante 1", "direccion rara", 1L, "314532", "logo url",
    10000L);

    public static final Restaurant NON_INSERTED_RESTAURANT = new Restaurant(2l, "Restaurante 2", "direccion rara", 1L, "314532", "logo url",
            10000L);
    public static final Restaurant NON_VALID_RESTAURANT = new Restaurant(3l, "1321421323", "direccion rara", 3L, "232314232131242", "logo url",
            10000L);
    public static final User OWNER = new User(1l, "Lalo", "Salamanca", "123",
            "lalo@gmail.com", "password", 2);
    public static final User NOT_A_OWNER = new User(2l, "Lalei", "Salamanca", "123",
            "lalei@gmail.com", "password", 3);
}
