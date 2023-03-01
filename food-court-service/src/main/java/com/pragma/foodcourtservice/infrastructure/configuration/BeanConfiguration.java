package com.pragma.foodcourtservice.infrastructure.configuration;

import com.pragma.foodcourtservice.application.mapper.FoodPlateDtoMapper;
import com.pragma.foodcourtservice.application.mapper.RestaurantDtoMapper;
import com.pragma.foodcourtservice.application.mapper.RolesDTOMapper;
import com.pragma.foodcourtservice.application.mapper.UserDtoMapper;
import com.pragma.foodcourtservice.domain.api.*;
import com.pragma.foodcourtservice.domain.spi.*;
import com.pragma.foodcourtservice.domain.useCase.FoodPlateUseCase;
import com.pragma.foodcourtservice.domain.useCase.OrderUseCase;
import com.pragma.foodcourtservice.domain.useCase.RestaurantUseCase;
import com.pragma.foodcourtservice.infrastructure.driven_adapter.*;
import com.pragma.foodcourtservice.infrastructure.microservices.adapters.IMessagingMicroServiceAdapter;
import com.pragma.foodcourtservice.infrastructure.microservices.adapters.UserMicroServiceClientAdapter;
import com.pragma.foodcourtservice.infrastructure.microservices.feign_client.MessagingFeignClientRest;
import com.pragma.foodcourtservice.infrastructure.microservices.feign_client.UserFeignClientRest;
import com.pragma.foodcourtservice.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.pragma.foodcourtservice.infrastructure.output.jpa.adapter.FoodPlateJpaAdapter;
import com.pragma.foodcourtservice.infrastructure.output.jpa.adapter.OrderJpaAdapter;
import com.pragma.foodcourtservice.infrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.*;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.*;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
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
    private final IOrderTicketRepository orderTicketRepository;
    @Bean
    public IPersistentLoggedUser persistentLoggedUser(){
        return new PersistentLoggedUser();
    }

    @Bean
    public IRestaurantValidator restaurantValidator(){
        return new RestaurantValidator();
    }
    @Bean
    public IFoodPlateValidator foodPlateValidator(){
        return new FoodPlateValidator();
    }
    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort(){
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEmployeeRepository, restaurantEntityMapper,
                restaurantEmployeeEntityMapper);
    }
    @Bean
    public IUserMicroServiceClientPort userClientPort(){
        return new UserMicroServiceClientAdapter(userFeignClientRest, userDtoMapper, rolesDTOMapper);
    }
    @Bean
    public IRestaurantServicePort restaurantServicePort(){
        return new RestaurantUseCase(restaurantPersistencePort(), restaurantValidator(), userClientPort(),
                persistentLoggedUser());
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
                foodPlateValidator(), persistentLoggedUser());
    }
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl(userClientPort(), persistentLoggedUser());
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
    private final IOrderRepository orderRepository;
    private final IOrderFoodPlatesRepository orderFoodPlatesRepository;
    private final OrderEntityMapper orderEntityMapper;
    @Bean
    public IOrderPersistencePort orderPersistencePort(){
        return new OrderJpaAdapter(orderRepository, orderTicketRepository, orderFoodPlatesRepository, orderEntityMapper);
    }

    private final MessagingFeignClientRest messagingFeignClientRest;
    @Bean
    public IMessagingMicroServiceClientPort messagingClientPort(){
        return new IMessagingMicroServiceAdapter(messagingFeignClientRest);
    }
    @Bean
    public IOrderNotifierPort orderNotifierPort(){
        return new OrderNotifierAdapter(messagingClientPort(), userClientPort(),
                restaurantPersistencePort(), orderPersistencePort());
    }
    @Bean
    public IOrderServicePort orderServicePort(){
        return new OrderUseCase(orderPersistencePort(),foodPlatePersistencePort(),
                persistentLoggedUser(), restaurantPersistencePort(), orderNotifierPort());
    }


}
