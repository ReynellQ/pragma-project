package com.pragma.foodcourtservice.infrastructure.output.jpa.adapter;

import com.pragma.foodcourtservice.domain.model.Restaurantes;
import com.pragma.foodcourtservice.domain.spi.IRestaurantePersistencePort;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.RestauranteEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IRestauranteRepository;

public class RestauranteJpaAdapter implements IRestaurantePersistencePort {
    private final IRestauranteRepository restauranteRepository;
    private final RestauranteEntityMapper restauranteEntityMapper;

    public RestauranteJpaAdapter(IRestauranteRepository restauranteRepository,
                                 RestauranteEntityMapper restauranteEntityMapper) {
        this.restauranteRepository = restauranteRepository;
        this.restauranteEntityMapper = restauranteEntityMapper;
    }

    @Override
    public void saveRestaurantes(Restaurantes restaurantes) {
        restauranteRepository.save(restauranteEntityMapper.toEntity(restaurantes));
    }
}
