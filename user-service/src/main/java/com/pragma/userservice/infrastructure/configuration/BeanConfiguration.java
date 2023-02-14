package com.pragma.userservice.infrastructure.configuration;

import com.pragma.userservice.domain.api.IPersonaChecker;
import com.pragma.userservice.domain.api.IPersonaServicePort;
import com.pragma.userservice.domain.spi.IPersonaPersistencePort;
import com.pragma.userservice.domain.spi.IRolesPersistencePort;
import com.pragma.userservice.domain.useCase.PersonaUseCase;
import com.pragma.userservice.infrastructure.output.jpa.adapter.PersonaJpaAdapter;
import com.pragma.userservice.infrastructure.output.jpa.adapter.RolesJpaAdapter;
import com.pragma.userservice.infrastructure.output.jpa.mapper.PersonaEntityMapper;
import com.pragma.userservice.infrastructure.output.jpa.mapper.RolesEntityMapper;
import com.pragma.userservice.infrastructure.output.jpa.repository.IPersonaRepository;
import com.pragma.userservice.infrastructure.output.jpa.repository.IRolesRepository;
import com.pragma.userservice.infrastructure.thirdparty.PersonaChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IPersonaRepository personaRepository;
    private final PersonaEntityMapper personaEntityMapper;
    private final RolesEntityMapper rolesEntityMapper;
    private final IRolesRepository rolesRepository;
    @Bean
    public IRolesPersistencePort rolesPersistencePort(){
        return new RolesJpaAdapter(rolesEntityMapper, rolesRepository);
    }
    @Bean
    public IPersonaChecker personaChecker(){
        return new PersonaChecker();
    }
    @Bean
    public IPersonaPersistencePort personaPersistencePort(){
        return new PersonaJpaAdapter(personaRepository,personaEntityMapper);
    }
    @Bean
    public IPersonaServicePort personaServicePort(){
        return new PersonaUseCase(personaPersistencePort(), rolesPersistencePort(), personaChecker());
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }


}
