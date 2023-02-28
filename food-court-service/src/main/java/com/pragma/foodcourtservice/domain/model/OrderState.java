package com.pragma.foodcourtservice.domain.model;

import java.util.Set;

public class OrderState { //change to class with constants

    public static final int PENDING = 1;
    public static final int CANCELLLED = -1;
    public static final int READY = 2;
    public static final int DELIVERED = 3;
    public static final Set<Integer> states = Set.of(PENDING, CANCELLLED, READY, DELIVERED);

}
