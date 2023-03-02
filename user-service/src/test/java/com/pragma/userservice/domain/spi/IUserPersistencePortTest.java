package com.pragma.userservice.domain.spi;

import com.pragma.userservice.UserData;
import com.pragma.userservice.domain.model.User;
import com.pragma.userservice.infrastructure.exception.UserConflictForEmailException;
import com.pragma.userservice.infrastructure.exception.UserConflictForIdException;
import com.pragma.userservice.infrastructure.exception.UserNotFoundException;
import com.pragma.userservice.infrastructure.output.jpa.adapter.UserJpaAdapter;
import com.pragma.userservice.infrastructure.output.jpa.entity.UserEntity;
import com.pragma.userservice.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.pragma.userservice.infrastructure.output.jpa.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.pragma.userservice.Utils.cloneUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IUserPersistencePortTest {
    IUserRepository userRepository;
    UserEntityMapper userEntityMapper;

    IUserPersistencePort persistencePort;

    /**
     * Set up the userPersistencePort and their dependencies in mocks, in order to unit test UserUseCase
     */
    @BeforeEach
    void setUp(){
        userRepository = mock(IUserRepository.class);
        userEntityMapper = mock(UserEntityMapper.class);
        persistencePort = new UserJpaAdapter(userRepository, userEntityMapper);
    }


    @Test
    void getAnExistingUserWithIdProvided() {
        UserEntity userEntity = new UserEntity(1L, 1L, "Lalo", "Salamanca", "123",
                "lalo@gmail.com", "password", 2);
        User user = new User(1l, 1l, "Lalo", "Salamanca", "123",
                "lalo@gmail.com", "password", 2);
        when(userEntityMapper.toUser(userEntity)).thenReturn(user);
        when(userRepository.findByPersonalId(userEntity.getPersonalId())).thenReturn(Optional.of(userEntity));
        assertEquals(user, persistencePort.getUserByPersonalId(user.getPersonalId()));
    }

    @Test
    void getAnNonExistingUserWithIdProvided() {
        UserEntity userEntity = new UserEntity(2L, 1L, "Lalo", "Salamanca", "123",
                "lalo@gmail.com", "password", 2);
        User user = new User(1l, 1l, "Lalo", "Salamanca", "123",
                "lalo@gmail.com", "password", 2);
        when(userRepository.findByPersonalId(user.getPersonalId())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,
                ()->persistencePort.getUserByPersonalId(user.getPersonalId()));
    }

    @Test
    void getAnExistingUserWithEmailProvided() {
        UserEntity userEntity = new UserEntity(1L, 1L, "Lalo", "Salamanca", "123",
                "lalo@gmail.com", "password", 2);
        User user = new User(1l, 1l, "Lalo", "Salamanca", "123",
                "lalo@gmail.com", "password", 2);
        when(userEntityMapper.toUser(userEntity)).thenReturn(user);
        when(userRepository.findByEmail(userEntity.getEmail())).thenReturn(Optional.of(userEntity));
        assertEquals(user, persistencePort.getUserByEmail(user.getEmail()));
    }

    @Test
    void getAnNonExistingUserWithEmailProvided() {
        UserEntity userEntity = new UserEntity(2L, 1L, "Lalo", "Salamanca", "123",
                "lalo@gmail.com", "password", 2);
        User user = new User(1l, 1l, "Lalo", "Salamanca", "123",
                "lalo@gmail.com", "password", 2);
        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class,
                ()->persistencePort.getUserByEmail(user.getEmail()));
    }

    @Test
    void saveUserCorrectly() {
        User u1 = cloneUser(UserData.NON_INSERTED_USER_001);
        u1.setIdRole(2); //At this point the idRole has been set.
        UserEntity entity = new UserEntity(3l,3l, "Walter", "White", "123",
                "walterwhite@gmail.com", "password", 2);
        when(userEntityMapper.toEntity(u1)).thenReturn(entity);
        when(userRepository.findByPersonalId(entity.getPersonalId())).thenReturn(Optional.empty()); //This user isn't inserted previously.
        when(userRepository.findByEmail(entity.getEmail()))
                .thenReturn(Optional.empty()); //This user isn't inserted previously.

        assertDoesNotThrow(()->persistencePort.saveUser(u1)); //Insert the user
    }

    @Test
    void saveUserButConflictWithId() {
        User u = cloneUser(UserData.USER_001);
        UserEntity entity = new UserEntity(u.getId(), u.getPersonalId(), u.getName(), u.getLastname(), u.getPhone(),
                u.getEmail(), u.getPassword(), u.getIdRole());
        when(userRepository.findByPersonalId(entity.getPersonalId() ))
                .thenReturn(Optional.of(entity)); //The user is registered.
        when(userRepository.findByEmail(entity.getEmail()))
                .thenReturn(Optional.of(entity)); //This user isn't inserted previously.
        assertThrows(UserConflictForIdException.class,
                ()->persistencePort.saveUser(u)); //Cannot insert the user because was previously inserted.
    }

    @Test
    void saveUserButConflictWithEmail(){
        User u = cloneUser(UserData.USER_001);
        UserEntity entity = new UserEntity(u.getId(), u.getPersonalId(), u.getName(), u.getLastname(), u.getPhone(),
                u.getEmail(), u.getPassword(), u.getIdRole());

        when(userRepository.findByPersonalId(entity.getId()))
                .thenReturn(Optional.of(entity)); //The user is registered.
        when(userRepository.findByEmail(entity.getEmail()))
                .thenReturn(Optional.of(entity)); //This user isn't inserted previously.

        //The id changes
        u.setPersonalId(1000l);
        assertThrows(UserConflictForEmailException.class,
                ()->persistencePort.saveUser(u)); //Cannot insert a user with the same email was previously inserted.
    }
}