package com.pragma.foodcourtservice.domain.api;

import com.pragma.foodcourtservice.domain.model.Restaurantes;

public interface IRestauranteServicePort {
    void saveRestaurantes(Restaurantes restaurantes);
}
