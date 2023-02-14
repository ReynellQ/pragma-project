package com.pragma.foodcourtservice.domain.api;

public interface IPlatoValidator {
    boolean validatesIdRestaurant(Long id);
    boolean validatesPrecio(Long precio);
}
