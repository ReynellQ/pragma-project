package com.pragma.userservice.infrastructure.configuration;

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
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public IPersonaPersistencePort personaPersistencePort(){
        return new PersonaJpaAdapter(personaRepository,personaEntityMapper);
    }
    @Bean
    public IPersonaServicePort personaServicePort(){
        return new PersonaUseCase(personaPersistencePort(), rolesPersistencePort());
    }
}
