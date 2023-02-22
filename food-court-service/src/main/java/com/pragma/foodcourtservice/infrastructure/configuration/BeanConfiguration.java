package com.pragma.foodcourtservice.infrastructure.configuration;

import com.pragma.foodcourtservice.application.mapper.FoodPlateDtoMapper;
import com.pragma.foodcourtservice.application.mapper.RestaurantDtoMapper;
import com.pragma.foodcourtservice.application.mapper.RolesDTOMapper;
import com.pragma.foodcourtservice.application.mapper.UserDtoMapper;
import com.pragma.foodcourtservice.domain.api.IFoodPlateServicePort;
import com.pragma.foodcourtservice.domain.api.IFoodPlateValidator;
import com.pragma.foodcourtservice.domain.api.IRestaurantServicePort;
import com.pragma.foodcourtservice.domain.api.IRestaurantValidator;
import com.pragma.foodcourtservice.domain.spi.ICategoryPersistencePort;
import com.pragma.foodcourtservice.domain.spi.IUserMicroServiceClientPort;
import com.pragma.foodcourtservice.domain.spi.IFoodPlatePersistencePort;
import com.pragma.foodcourtservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourtservice.domain.useCase.FoodPlateUseCase;
import com.pragma.foodcourtservice.domain.useCase.RestaurantUseCase;
import com.pragma.foodcourtservice.infrastructure.driven_adapter.*;
import com.pragma.foodcourtservice.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.pragma.foodcourtservice.infrastructure.output.jpa.adapter.FoodPlateJpaAdapter;
import com.pragma.foodcourtservice.infrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.CategoryEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.FoodPlateEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.RestaurantEmployeeEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.ICategoryRepository;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IFoodPlateRepository;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IRestaurantEmployeeRepository;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IRestaurantRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfiguration {
    private final FoodPlateDtoMapper foodPlateDTOMapper;

    private final RestaurantDtoMapper restaurantDTOMapper;
    private final RolesDTOMapper rolesDTOMapper;
    private final IRestaurantEmployeeRepository restaurantEmployeeRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final FoodPlateEntityMapper foodPlateEntityMapper;
    private final CategoryEntityMapper categoryEntityMapper;
    private final IRestaurantRepository restaurantRepository;
    private final IFoodPlateRepository foodPlateRepository;
    private final ICategoryRepository categoryRepository;
    private final UserDtoMapper userDtoMapper;
    private final UserFeignClientRest userFeignClientRest;
    private final AuthenticationConfiguration authConfiguration;
    private final RestaurantEmployeeEntityMapper restaurantEmployeeEntityMapper;


    public BeanConfiguration(FoodPlateDtoMapper foodPlateDTOMapper, RestaurantDtoMapper restaurantDTOMapper,
                             RolesDTOMapper rolesDTOMapper, IRestaurantEmployeeRepository restaurantEmployeeRepository, RestaurantEntityMapper restaurantEntityMapper, FoodPlateEntityMapper foodPlateEntityMapper,
                             CategoryEntityMapper categoryEntityMapper, IRestaurantRepository restaurantRepository, IFoodPlateRepository foodPlateRepository, ICategoryRepository categoryRepository, UserDtoMapper userDtoMapper, UserFeignClientRest userFeignClientRest, AuthenticationConfiguration authConfiguration, RestaurantEmployeeEntityMapper restaurantEmployeeEntityMapper) {
        this.foodPlateDTOMapper = foodPlateDTOMapper;
        this.restaurantDTOMapper = restaurantDTOMapper;
        this.rolesDTOMapper = rolesDTOMapper;
        this.restaurantEmployeeRepository = restaurantEmployeeRepository;
        this.restaurantEntityMapper = restaurantEntityMapper;
        this.foodPlateEntityMapper = foodPlateEntityMapper;
        this.categoryEntityMapper = categoryEntityMapper;
        this.restaurantRepository = restaurantRepository;
        this.foodPlateRepository = foodPlateRepository;
        this.categoryRepository = categoryRepository;
        this.userDtoMapper = userDtoMapper;
        this.userFeignClientRest = userFeignClientRest;
        this.authConfiguration = authConfiguration;
        this.restaurantEmployeeEntityMapper = restaurantEmployeeEntityMapper;
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
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEmployeeRepository, restaurantEntityMapper, restaurantEmployeeEntityMapper);
    }
    @Bean
    public IUserMicroServiceClientPort userClientPort(){
        return new UserMicroServiceClientAdapter(userFeignClientRest, userDtoMapper, rolesDTOMapper);
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
    public ICategoryPersistencePort categoryPersistencePort(){
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }
    @Bean
    public IFoodPlateServicePort foodPlateServicePort(){
        return new FoodPlateUseCase(restaurantPersistencePort(), categoryPersistencePort(), foodPlatePersistencePort(),
                foodPlateValidator(), userClientPort());
    }
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl(userClientPort());
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager() {
        try {
            return authConfiguration.getAuthenticationManager();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
