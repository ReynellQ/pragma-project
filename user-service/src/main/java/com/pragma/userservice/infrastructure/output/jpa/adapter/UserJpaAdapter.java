package com.pragma.userservice.infrastructure.output.jpa.adapter;

import com.pragma.userservice.domain.model.User;
import com.pragma.userservice.domain.spi.IUserPersistencePort;
import com.pragma.userservice.infrastructure.exception.UserConflictForEmailException;
import com.pragma.userservice.infrastructure.exception.UserConflictForIdException;
import com.pragma.userservice.infrastructure.output.jpa.entity.UserEntity;
import com.pragma.userservice.infrastructure.exception.UserNotFoundException;
import com.pragma.userservice.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.pragma.userservice.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
/**
 * The UserJpaAdapter, that implements the UserServicePort. Uses the Spring Jpa technology.
 */
@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    /**
     * Gets a user that has the id provided. Search in the JPA repository and map the entity User to the User domain
     * model.
     * @param id the id of the user searched.
     * @return the User with the id provided.
     */
    @Override
    public User getUserByPersonalId(Long id) {
        Optional<UserEntity> personaEntity = userRepository.findByPersonalId(id);
        if(personaEntity.isEmpty())
            throw new UserNotFoundException();
        return userEntityMapper.toUser(personaEntity.get());
    }

    /**
     * Gets a user that has the id provided.
     *
     * @param id the id of the user searched.
     * @return the User with the id provided.
     */
    @Override
    public User getUserById(Long id) {
        Optional<UserEntity> personaEntity = userRepository.findById(id);
        if(personaEntity.isEmpty())
            throw new UserNotFoundException();
        return userEntityMapper.toUser(personaEntity.get());
    }

    /**
     * Gets a user that has the email provided. It's assumed that aren't more the one user with the same email.
     * Search in the JPA repository and map the entity User to the User domain model.
     * @param email the email of the user to search.
     * @return the User with the provided email.
     */
    @Override
    public User getUserByEmail(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        if(userEntity.isEmpty())
            throw new UserNotFoundException();
        return userEntityMapper.toUser(userEntity.get());
    }
    /**
     * Saves a user in the persistence layer. Saves in the JPA repository
     * @param userModel the user to the saved.
     */
    @Override
    public void saveUser(User userModel) {
        Optional<UserEntity> userEntity = userRepository.findByPersonalId(userModel.getPersonalId());
        if(userEntity.isPresent())
            throw new UserConflictForIdException();
        userEntity = userRepository.findByEmail(userModel.getEmail());
        if(userEntity.isPresent())
            throw new UserConflictForEmailException();
        UserEntity pe = userEntityMapper.toEntity(userModel);
        userRepository.save(pe);
    }
}
