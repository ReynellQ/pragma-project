package com.pragma.userservice.infrastructure.output.jpa.adapter;

import com.pragma.userservice.domain.model.User;
import com.pragma.userservice.domain.spi.IUserPersistencePort;
import com.pragma.userservice.infrastructure.exception.UserWithEmailAlreadyExists;
import com.pragma.userservice.infrastructure.exception.UserWithIDAlreadyExistsException;
import com.pragma.userservice.infrastructure.output.jpa.entity.UserEntity;
import com.pragma.userservice.infrastructure.exception.UserDoesntExistsException;
import com.pragma.userservice.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.pragma.userservice.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
/**
 * The UserJpaAdapter, that implements the UserServicePort. Uses the Spring Jpa technology.
 */
@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {
    private final IUserRepository personaRepository;
    private final UserEntityMapper userEntityMapper;
    /**
     * Gets a user that has the id provided. Search in the JPA repository and map the entity User to the User domain
     * model.
     * @param id the id of the user searched.
     * @return the User with the id provided.
     */
    @Override
    public User getUser(Long id) {
        Optional<UserEntity> personaEntity = personaRepository.findById(id);
        if(personaEntity.isEmpty())
            throw new UserDoesntExistsException();
        return userEntityMapper.toPersona(personaEntity.get());
    }
    /**
     * Gets a user that has the email provided. It's assumed that aren't more the one user with the same email.
     * Search in the JPA repository and map the entity User to the User domain model.
     * @param email the email of the user to search.
     * @return the User with the provided email.
     */
    @Override
    public User getUserByEmail(String email) {
        Optional<UserEntity> personaEntity = personaRepository.findByEmail(email);
        if(personaEntity.isEmpty())
            throw new UserDoesntExistsException();
        return userEntityMapper.toPersona(personaEntity.get());
    }
    /**
     * Saves a user in the persistence layer. Saves in the JPA repository
     * @param userModel the user to the saved.
     */
    @Override
    public void saveUser(User userModel) {
        Optional<UserEntity> personaEntity = personaRepository.findById(userModel.getId());
        if(personaEntity.isPresent())
            throw new UserWithIDAlreadyExistsException();
        personaEntity = personaRepository.findByEmail(userModel.getEmail());
        if(personaEntity.isPresent())
            throw new UserWithEmailAlreadyExists();
        UserEntity pe = userEntityMapper.toEntity(userModel);
        pe.setId(userModel.getId());
        personaRepository.save(pe);
    }
}
