package com.pragma.foodcourtservice.domain.useCase;


import com.pragma.foodcourtservice.domain.api.IRestauranteServicePort;
import com.pragma.foodcourtservice.domain.api.IRestauranteValidator;
import com.pragma.foodcourtservice.domain.exception.BadData;
import com.pragma.foodcourtservice.domain.model.Restaurantes;
import com.pragma.foodcourtservice.domain.spi.IRestaurantePersistencePort;

public class RestauranteUseCase implements IRestauranteServicePort {
    private final IRestaurantePersistencePort restaurantePersistencePort;
    private final IRestauranteValidator restauranteValidator;

    public RestauranteUseCase(IRestaurantePersistencePort restaurantePersistencePort, IRestauranteValidator restauranteValidator) {
        this.restaurantePersistencePort = restaurantePersistencePort;
        this.restauranteValidator = restauranteValidator;
    }

    @Override
    public void saveRestaurantes(Restaurantes restaurantes) {
        if(!restauranteValidator.validateOwner(restaurantes.getIdPropietario()))
            throw new BadData();
        if(!restauranteValidator.validatePhone(restaurantes.getTelefono()))
            throw new BadData();
        if(!restauranteValidator.validateName(restaurantes.getNombre()))
            throw new BadData();
        restaurantePersistencePort.saveRestaurantes(restaurantes);
    }
}
