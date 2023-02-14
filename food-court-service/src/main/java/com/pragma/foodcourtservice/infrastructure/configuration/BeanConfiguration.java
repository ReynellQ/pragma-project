package com.pragma.foodcourtservice.infrastructure.configuration;

import com.pragma.foodcourtservice.application.mapper.PlatosDTOMapper;
import com.pragma.foodcourtservice.application.mapper.RestauranteDTOMapper;
import com.pragma.foodcourtservice.domain.api.IPlatoServicePort;
import com.pragma.foodcourtservice.domain.api.IPlatoValidator;
import com.pragma.foodcourtservice.domain.api.IRestauranteServicePort;
import com.pragma.foodcourtservice.domain.api.IRestauranteValidator;
import com.pragma.foodcourtservice.domain.spi.IPlatoPersistencePort;
import com.pragma.foodcourtservice.domain.spi.IRestaurantePersistencePort;
import com.pragma.foodcourtservice.domain.useCase.PlatoUseCase;
import com.pragma.foodcourtservice.domain.useCase.RestauranteUseCase;
import com.pragma.foodcourtservice.infrastructure.output.jpa.adapter.PlatoJpaAdapter;
import com.pragma.foodcourtservice.infrastructure.output.jpa.adapter.RestauranteJpaAdapter;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.PlatoEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.RestauranteEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IPlatoRepository;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IRestauranteRepository;
import com.pragma.foodcourtservice.infrastructure.thirdparty.PlatoValidator;
import com.pragma.foodcourtservice.infrastructure.thirdparty.RestauranteValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfiguration {
    private final PlatosDTOMapper platosDTOMapper;

    private final RestauranteDTOMapper restauranteDTOMapper;

    private final RestauranteEntityMapper restauranteEntityMapper;
    private final PlatoEntityMapper platoEntityMapper;
    private final IRestauranteRepository restauranteRepository;
    private final IPlatoRepository platoRepository;


    public BeanConfiguration(PlatosDTOMapper platosDTOMapper, RestauranteDTOMapper restauranteDTOMapper,
                             RestauranteEntityMapper restauranteEntityMapper, PlatoEntityMapper platoEntityMapper,
                             IRestauranteRepository restauranteRepository, IPlatoRepository platoRepository) {
        this.platosDTOMapper = platosDTOMapper;
        this.restauranteDTOMapper = restauranteDTOMapper;
        this.restauranteEntityMapper = restauranteEntityMapper;
        this.platoEntityMapper = platoEntityMapper;
        this.restauranteRepository = restauranteRepository;
        this.platoRepository = platoRepository;
    }

    @Bean
    public RestTemplate connectionToUserService(){
        return new RestTemplate();
    }
    @Bean
    public IRestauranteValidator restauranteValidator(){
        return new RestauranteValidator(connectionToUserService());
    }
    @Bean
    public IPlatoValidator platoValidator(){
        return new PlatoValidator(restauranteRepository);
    }
    @Bean
    public IRestaurantePersistencePort restaurantePersistencePort(){
        return new RestauranteJpaAdapter(restauranteRepository, restauranteEntityMapper);
    }
    @Bean
    public IRestauranteServicePort restauranteServicePort(){
        return new RestauranteUseCase(restaurantePersistencePort(), restauranteValidator());
    }
    @Bean
    public IPlatoPersistencePort platoPersistencePort(){
        return new PlatoJpaAdapter(platoRepository, platoEntityMapper);
    }
    @Bean
    public IPlatoServicePort platoServicePort(){
        return new PlatoUseCase(platoPersistencePort(), platoValidator());
    }






}
