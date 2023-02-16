package com.pragma.foodcourtservice.infrastructure.configuration;

import com.pragma.foodcourtservice.application.mapper.FoodPlateDtoMapper;
import com.pragma.foodcourtservice.application.mapper.RestaurantDTOMapper;
import com.pragma.foodcourtservice.application.mapper.UserDtoMapper;
import com.pragma.foodcourtservice.domain.api.IFoodPlateServicePort;
import com.pragma.foodcourtservice.domain.api.IFoodPlateValidator;
import com.pragma.foodcourtservice.domain.api.IRestaurantServicePort;
import com.pragma.foodcourtservice.domain.api.IRestaurantValidator;
import com.pragma.foodcourtservice.domain.spi.IUserMicroServiceClientPort;
import com.pragma.foodcourtservice.domain.spi.IFoodPlatePersistencePort;
import com.pragma.foodcourtservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourtservice.domain.useCase.FoodPlateUseCase;
import com.pragma.foodcourtservice.domain.useCase.RestaurantUseCase;
import com.pragma.foodcourtservice.infrastructure.output.jpa.adapter.FoodPlateJpaAdapter;
import com.pragma.foodcourtservice.infrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.FoodPlateEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.RestauranteEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IFoodPlateRepository;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IRestaurantRepository;
import com.pragma.foodcourtservice.infrastructure.thirdparty.UserMicroServiceClientAdapter;
import com.pragma.foodcourtservice.infrastructure.thirdparty.FoodPlateValidator;
import com.pragma.foodcourtservice.infrastructure.thirdparty.RestaurantValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfiguration {
    private final FoodPlateDtoMapper foodPlateDTOMapper;

    private final RestaurantDTOMapper restaurantDTOMapper;

    private final RestauranteEntityMapper restauranteEntityMapper;
    private final FoodPlateEntityMapper foodPlateEntityMapper;
    private final IRestaurantRepository restauranteRepository;
    private final IFoodPlateRepository platoRepository;
    private final UserDtoMapper userDtoMapper;


    public BeanConfiguration(FoodPlateDtoMapper foodPlateDTOMapper, RestaurantDTOMapper restaurantDTOMapper,
                             RestauranteEntityMapper restauranteEntityMapper, FoodPlateEntityMapper foodPlateEntityMapper,
                             IRestaurantRepository restauranteRepository, IFoodPlateRepository platoRepository, UserDtoMapper userDtoMapper) {
        this.foodPlateDTOMapper = foodPlateDTOMapper;
        this.restaurantDTOMapper = restaurantDTOMapper;
        this.restauranteEntityMapper = restauranteEntityMapper;
        this.foodPlateEntityMapper = foodPlateEntityMapper;
        this.restauranteRepository = restauranteRepository;
        this.platoRepository = platoRepository;
        this.userDtoMapper = userDtoMapper;
    }

    @Bean
    public RestTemplate connectionToUserService(){
        return new RestTemplate();
    }
    @Bean
    public IRestaurantValidator restauranteValidator(){
        return new RestaurantValidator();
    }
    @Bean
    public IFoodPlateValidator platoValidator(){
        return new FoodPlateValidator(restauranteRepository);
    }
    @Bean
    public IRestaurantPersistencePort restaurantePersistencePort(){
        return new RestaurantJpaAdapter(restauranteRepository, restauranteEntityMapper);
    }
    @Bean
    public IUserMicroServiceClientPort personaClientPort(){
        return new UserMicroServiceClientAdapter(connectionToUserService(), userDtoMapper);
    }
    @Bean
    public IRestaurantServicePort restauranteServicePort(){
        return new RestaurantUseCase(restaurantePersistencePort(), restauranteValidator(), personaClientPort());
    }
    @Bean
    public IFoodPlatePersistencePort platoPersistencePort(){
        return new FoodPlateJpaAdapter(platoRepository, foodPlateEntityMapper);
    }
    @Bean
    public IFoodPlateServicePort platoServicePort(){
        return new FoodPlateUseCase(platoPersistencePort(), platoValidator());
    }






}
