package com.pragma.foodcourtservice.domain.model;

public enum ORDER_STATE {
    PENDING(1),
    CANCELLED(-1),
    READY(2),
    DELIVERED(3);
    public int id;
    ORDER_STATE(int id) {
        this.id = id;
    }
}
