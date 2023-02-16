package com.pragma.userservice;

import com.pragma.userservice.domain.api.IAuth;
import com.pragma.userservice.domain.api.IUserValidator;
import com.pragma.userservice.domain.model.User;
import com.pragma.userservice.domain.spi.IUserPersistencePort;
import com.pragma.userservice.domain.useCase.UserUseCase;
import com.pragma.userservice.infrastructure.exception.UserWithEmailAlreadyExistsException;
import com.pragma.userservice.infrastructure.exception.UserWithIDAlreadyExistsException;
import com.pragma.userservice.infrastructure.output.jpa.adapter.UserJpaAdapter;
import com.pragma.userservice.infrastructure.output.jpa.entity.UserEntity;
import com.pragma.userservice.infrastructure.output.jpa.mapper.UserEntityMapper;
import com.pragma.userservice.infrastructure.output.jpa.repository.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class IUserPersistencePortTests {
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

    /**
     * Save a user that doesn't exist.
     * Save a user has 3 cases:
     * - A user with the same id already exists, throw a UserWithIDAlreadyExistsException
     * - a user with the same email already exists, throws a UserWithEmailAlreadyExistsException
     * - in another case, save the user correctly.
     *
     * If the user U is saved, it cannot be saved again if the id or the email is the same as other user in the database
     * and this user is, effectively, the user U. If that data changes, the user can be saved again because isn't the
     * same user anymore.
     */
    @Test
    void saveAUser(){
        User u1 = UserData.NON_INSERTED_USER_001;
        u1.setIdRole(2); //At this point the idRole has been set.
        UserEntity userRepo = new UserEntity(3l, "Walter", "White", "123",
                "walterwhite@gmail.com", "password", 2);
        when(userEntityMapper.toEntity(u1)).thenReturn(userRepo);
        when(userRepository.findById(u1.getId())).thenReturn(Optional.empty()); //This user isn't inserted previously.
        when(userRepository.findByEmail(u1.getEmail()))
                .thenReturn(Optional.empty()); //This user isn't inserted previously.
        Answer<Void> answer = invocation ->{
            when(userRepository.findById(3l)).thenReturn(Optional.of(userRepo));
            when(userRepository.findByEmail(u1.getEmail())).thenReturn(Optional.of(userRepo));
            return null;
        };

        doAnswer(answer).when(userRepository)  //If It's inserted, must be found
                .save(userRepo);

        assertDoesNotThrow(()->persistencePort.saveUser(u1)); //Insert the user
        assertThrows(UserWithIDAlreadyExistsException.class,
                ()->persistencePort.saveUser(u1)); //Cannot insert the user because was previously inserted.
        //Even if the id's change, the email is the same and cannot be saved.
        u1.setId(2839123213l);
        assertThrows(UserWithEmailAlreadyExistsException.class,
                ()->persistencePort.saveUser(u1)); //Cannot insert the user because was previously inserted.

        //Changing both, technically is not the same user.
        u1.setEmail("correoxdddxdd@gmail.com");
        assertDoesNotThrow(()->persistencePort.saveUser(u1)); //Insert the user
    }




}
