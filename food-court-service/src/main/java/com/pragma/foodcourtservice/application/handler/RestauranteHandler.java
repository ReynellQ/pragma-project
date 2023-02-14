package com.pragma.foodcourtservice.application.handler;

import com.pragma.foodcourtservice.application.dto.RestauranteDTO;
import com.pragma.foodcourtservice.application.mapper.RestauranteDTOMapper;
import com.pragma.foodcourtservice.domain.api.IRestauranteServicePort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class RestauranteHandler implements IRestauranteHandler{
    private final IRestauranteServicePort restauranteServicePort;
    private final RestauranteDTOMapper restauranteDTO;

    public RestauranteHandler(IRestauranteServicePort restauranteServicePort, RestauranteDTOMapper restauranteDTO) {
        this.restauranteServicePort = restauranteServicePort;
        this.restauranteDTO = restauranteDTO;
    }
    //Manage here if the DTO has some attribute null?
    @Override
    public void saveRestaurantes(RestauranteDTO restaurantes) {
        restauranteServicePort.saveRestaurantes(restauranteDTO.toRestaurante(restaurantes));
    }
}
