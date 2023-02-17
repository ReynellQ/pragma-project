package com.pragma.userservice.infrastructure.configuration;

import com.pragma.userservice.domain.api.IAuth;
import com.pragma.userservice.domain.api.IUserValidator;
import com.pragma.userservice.domain.api.IUserServicePort;
import com.pragma.userservice.domain.spi.IRolesPersistencePort;
import com.pragma.userservice.domain.spi.IUserPersistencePort;
import com.pragma.userservice.domain.useCase.UserUseCase;
import com.pragma.userservice.infrastructure.output.jpa.adapter.RoleJpaAdapter;
import com.pragma.userservice.infrastructure.output.jpa.adapter.UserJpaAdapter;
import com.pragma.userservice.infrastructure.output.jpa.mapper.RolesEntityMapper;
import com.pragma.userservice.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.pragma.userservice.infrastructure.output.jpa.repository.IRolesRepository;
import com.pragma.userservice.infrastructure.output.jpa.repository.IUserRepository;
import com.pragma.userservice.infrastructure.thirdparty.AuthService;
import com.pragma.userservice.infrastructure.thirdparty.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The Bean Configuration class. Inserts the needed beans.
 */
@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository personaRepository;
    private final IRolesRepository rolesRepository;
    private final UserEntityMapper userEntityMapper;
    private final RolesEntityMapper rolesEntityMapper;
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public IAuth authService(){
        return new AuthService(encoder());
    }
    @Bean
    public IUserValidator personaChecker(){
        return new UserValidator();
    }

    @Bean
    public IUserPersistencePort personaPersistencePort(){
        return new UserJpaAdapter(personaRepository, userEntityMapper);
    }
    @Bean
    public IUserServicePort personaServicePort(){
        return new UserUseCase(personaPersistencePort(), rolesPersistencePort(), personaChecker(), authService());
    }
    @Bean
    IRolesPersistencePort rolesPersistencePort(){
        return new RoleJpaAdapter(rolesRepository, rolesEntityMapper);
    }




}
