package com.pragma.userservice.infrastructure.configuration;

import com.pragma.userservice.domain.api.IAuth;
import com.pragma.userservice.domain.api.IUserValidator;
import com.pragma.userservice.domain.api.IUserServicePort;
import com.pragma.userservice.domain.spi.IRolesPersistencePort;
import com.pragma.userservice.domain.spi.IUserPersistencePort;
import com.pragma.userservice.domain.useCase.UserUseCase;
import com.pragma.userservice.infrastructure.configuration.jwt.JwtService;
import com.pragma.userservice.infrastructure.driven_adapters.AuthService;
import com.pragma.userservice.infrastructure.output.jpa.adapter.RoleJpaAdapter;
import com.pragma.userservice.infrastructure.output.jpa.adapter.UserJpaAdapter;
import com.pragma.userservice.infrastructure.output.jpa.mapper.RolesEntityMapper;
import com.pragma.userservice.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.pragma.userservice.infrastructure.output.jpa.repository.IRolesRepository;
import com.pragma.userservice.infrastructure.output.jpa.repository.IUserRepository;
import com.pragma.userservice.infrastructure.driven_adapters.UserValidator;
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

/**
 * The Bean Configuration class. Inserts the needed beans.
 */
@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final IRolesRepository rolesRepository;
    private final UserEntityMapper userEntityMapper;
    private final RolesEntityMapper rolesEntityMapper;
    private final JwtService jwtService;
    private final AuthenticationConfiguration authConfiguration;
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IUserValidator userValidator(){
        return new UserValidator();
    }

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsServiceImpl(userPersistencePort());
    }
    @Bean
    public IAuth authService() {
        return new AuthService(passwordEncoder(), jwtService, authenticationManager(),
                (UserDetailsServiceImpl) userDetailsService());
    }
    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort(), rolesPersistencePort(), userValidator(), authService());
    }

    @Bean
    public IRolesPersistencePort rolesPersistencePort(){
        return new RoleJpaAdapter(rolesRepository, rolesEntityMapper);
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
