package com.pragma.userservice.infrastructure.output.jpa.adapter;

import com.pragma.userservice.domain.model.Persona;
import com.pragma.userservice.domain.spi.IPersonaPersistencePort;
import com.pragma.userservice.infrastructure.exception.IncorrectCredentials;
import com.pragma.userservice.infrastructure.exception.PersonWithEmailAlreadyExists;
import com.pragma.userservice.infrastructure.exception.PersonWithIDAlreadyExists;
import com.pragma.userservice.infrastructure.output.jpa.entity.PersonaEntity;
import com.pragma.userservice.infrastructure.exception.PersonDoesntExists;
import com.pragma.userservice.infrastructure.output.jpa.mapper.PersonaEntityMapper;
import com.pragma.userservice.infrastructure.output.jpa.repository.IPersonaRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PersonaJpaAdapter implements IPersonaPersistencePort {
    private final IPersonaRepository personaRepository;
    private final PersonaEntityMapper personaEntityMapper;

    @Override
    public Persona getPersona(Long id) {
        Optional<PersonaEntity> personaEntity = personaRepository.findById(id);
        if(personaEntity.isEmpty())
            throw new PersonDoesntExists();
        return personaEntityMapper.toPersona(personaEntity.get());
    }

    @Override
    public Persona getPersonaByEmail(String email) {
        Optional<PersonaEntity> personaEntity = personaRepository.findByCorreo(email);
        if(personaEntity.isEmpty())
            throw new PersonDoesntExists();
        return personaEntityMapper.toPersona(personaEntity.get());
    }

    @Override
    public Persona getPersonaByEmailAndPassword(String email, String password) {
        Optional<PersonaEntity> pe = personaRepository.findByCorreoAndClave(email, password);
        if(pe.isEmpty()) throw new IncorrectCredentials();
        return personaEntityMapper.toPersona(pe.get());
    }

    @Override
    public void savePersona(Persona personaModel) {
        Optional<PersonaEntity> personaEntity = personaRepository.findById(personaModel.getId());
        if(personaEntity.isPresent())
            throw new PersonWithIDAlreadyExists();
        personaEntity = personaRepository.findByCorreo(personaModel.getCorreo());
        if(personaEntity.isPresent())
            throw new PersonWithEmailAlreadyExists();
        PersonaEntity pe = personaEntityMapper.toEntity(personaModel);
        pe.setId(personaModel.getId());
        personaRepository.save(pe);
    }
}
