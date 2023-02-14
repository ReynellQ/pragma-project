package com.pragma.foodcourtservice.domain.spi;

import com.pragma.foodcourtservice.domain.model.Restaurantes;

public interface IRestaurantePersistencePort {
    void saveRestaurantes(Restaurantes restaurantes);
}
