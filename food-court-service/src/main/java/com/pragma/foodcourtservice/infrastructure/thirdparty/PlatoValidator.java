package com.pragma.foodcourtservice.infrastructure.thirdparty;

import com.pragma.foodcourtservice.domain.api.IPlatoValidator;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.RestaurantesEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IRestauranteRepository;

import java.util.Optional;

public class PlatoValidator implements IPlatoValidator {
    private final IRestauranteRepository restauranteRepository;

    public PlatoValidator(IRestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    @Override
    public boolean validatesIdRestaurant(Long id) {
        Optional<RestaurantesEntity> entity = restauranteRepository.findById(id);
        return entity.isPresent();
    }

    @Override
    public boolean validatesPrecio(Long precio) {
        return precio > 0;
    }
}
