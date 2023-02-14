package com.pragma.foodcourtservice.domain.api;

public interface IRestauranteValidator {

    boolean validateOwner(Long id);
    boolean validatePhone(String phone);
    boolean validateName(String nombre);
}
