package com.pragma.foodcourtservice.domain.model;

public enum ROLES {
    ADMIN(1),
    OWNER(2),
    EMPLOYEE(3),
    CLIENT(4);
    public final int id;
    ROLES(int id) {
        this.id = id;
    }
}
