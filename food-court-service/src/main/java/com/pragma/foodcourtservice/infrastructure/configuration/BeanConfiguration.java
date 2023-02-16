package com.pragma.foodcourtservice.infrastructure.configuration;

import com.pragma.foodcourtservice.application.mapper.FoodPlateDtoMapper;
import com.pragma.foodcourtservice.application.mapper.RestaurantDtoMapper;
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
import com.pragma.foodcourtservice.infrastructure.driven_adapter.UserFeignClientRest;
import com.pragma.foodcourtservice.infrastructure.output.jpa.adapter.FoodPlateJpaAdapter;
import com.pragma.foodcourtservice.infrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.FoodPlateEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IFoodPlateRepository;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IRestaurantRepository;
import com.pragma.foodcourtservice.infrastructure.driven_adapter.UserMicroServiceClientAdapter;
import com.pragma.foodcourtservice.infrastructure.driven_adapter.FoodPlateValidator;
import com.pragma.foodcourtservice.infrastructure.driven_adapter.RestaurantValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    private final FoodPlateDtoMapper foodPlateDTOMapper;

    private final RestaurantDtoMapper restaurantDTOMapper;

    private final RestaurantEntityMapper restaurantEntityMapper;
    private final FoodPlateEntityMapper foodPlateEntityMapper;
    private final IRestaurantRepository restaurantRepository;
    private final IFoodPlateRepository foodPlateRepository;
    private final UserDtoMapper userDtoMapper;
    private final UserFeignClientRest userFeignClientRest;


    public BeanConfiguration(FoodPlateDtoMapper foodPlateDTOMapper, RestaurantDtoMapper restaurantDTOMapper,
                             RestaurantEntityMapper restaurantEntityMapper, FoodPlateEntityMapper foodPlateEntityMapper,
                             IRestaurantRepository restaurantRepository, IFoodPlateRepository foodPlateRepository, UserDtoMapper userDtoMapper, UserFeignClientRest userFeignClientRest) {
        this.foodPlateDTOMapper = foodPlateDTOMapper;
        this.restaurantDTOMapper = restaurantDTOMapper;
        this.restaurantEntityMapper = restaurantEntityMapper;
        this.foodPlateEntityMapper = foodPlateEntityMapper;
        this.restaurantRepository = restaurantRepository;
        this.foodPlateRepository = foodPlateRepository;
        this.userDtoMapper = userDtoMapper;
        this.userFeignClientRest = userFeignClientRest;
    }


    @Bean
    public IRestaurantValidator restaurantValidator(){
        return new RestaurantValidator();
    }
    @Bean
    public IFoodPlateValidator foodPlateValidator(){
        return new FoodPlateValidator(restaurantRepository);
    }
    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
    }
    @Bean
    public IUserMicroServiceClientPort userClientPort(){
        return new UserMicroServiceClientAdapter(userFeignClientRest, userDtoMapper);
    }
    @Bean
    public IRestaurantServicePort restaurantServicePort(){
        return new RestaurantUseCase(restaurantPersistencePort(), restaurantValidator(), userClientPort());
    }
    @Bean
    public IFoodPlatePersistencePort foodPlatePersistencePort(){
        return new FoodPlateJpaAdapter(foodPlateRepository, foodPlateEntityMapper);
    }
    @Bean
    public IFoodPlateServicePort foodPlateServicePort(){
        return new FoodPlateUseCase(restaurantPersistencePort(), foodPlatePersistencePort(), foodPlateValidator());
    }

}
