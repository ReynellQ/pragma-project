package com.pragma.userservice.domain.api;

import com.pragma.userservice.UserData;
import com.pragma.userservice.domain.exception.IncorrectDataException;
import com.pragma.userservice.domain.model.User;
import com.pragma.userservice.domain.spi.IRolesPersistencePort;
import com.pragma.userservice.domain.spi.IUserPersistencePort;
import com.pragma.userservice.domain.useCase.UserUseCase;
import com.pragma.userservice.infrastructure.exception.UserConflictForEmailException;
import com.pragma.userservice.infrastructure.exception.UserConflictForIdException;
import com.pragma.userservice.infrastructure.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IUserServicePortTest {
    IRolesPersistencePort rolesPersistencePort;
    IUserPersistencePort persistencePort;
    IUserValidator userValidator;
    IAuthServicePort auth;

    IUserServicePort userServicePort;

    @BeforeEach
    void setUp(){
        persistencePort = mock(IUserPersistencePort.class);
        userValidator = mock(IUserValidator.class);
        auth = mock(IAuthServicePort.class);
        rolesPersistencePort = mock(IRolesPersistencePort.class);
        userServicePort = new UserUseCase(persistencePort, rolesPersistencePort, userValidator, auth);
    }


    @Test
    public void getAnExistingUserWithIdProvided() {
        User user = UserData.USER_001;
        when(persistencePort.getUserByPersonalId(user.getPersonalId()))
                .thenReturn(user);
        assertEquals( userServicePort.getUserByPersonalId(user.getPersonalId()), user);
    }

    @Test
    public void getAnNonExistingUserWithIdProvided() {
        User user = UserData.NON_INSERTED_USER_001;
        when(persistencePort.getUserByPersonalId(user.getPersonalId()))
                .thenThrow(UserNotFoundException.class);
        //If the user isn't saved, cannot be obtained and throws the UserNotFoundException.
        assertThrows(
                UserNotFoundException.class,
                () ->userServicePort.getUserByPersonalId(user.getPersonalId())
        );
    }


    @Test
    public void getAnExistingUserWithEmailProvided() {
        User user = UserData.USER_001;
        when(persistencePort.getUserByEmail(user.getEmail()))
                .thenReturn(user);
        assertEquals( userServicePort.getUserByEmail(user.getEmail()) , user);
    }

    @Test
    public void getAnNonExistingUserWithEmailProvided() {
        User u1 = UserData.NON_INSERTED_USER_001;
        when(persistencePort.getUserByEmail(u1.getEmail()))
                .thenThrow(UserNotFoundException.class);
        //If the user isn't saved, cannot be obtained and throws the UserNotFoundException.
        assertThrows(
                UserNotFoundException.class,
                () ->userServicePort.getUserByEmail(u1.getEmail())
        );
    }

    @Test
    public void goodCredentials() {
        String email = "good@email.com";
        String password = "correctpassword";
        String jwt = "bXl1c2VyaXNub3dhdXRoZW50aWNhdGVk";
        when(auth.authenticateUser(email, password)).thenReturn(jwt);
        userServicePort.authUser(email, password);
    }

    @Test
    public void badCredentials() {
        String email = "good@email.com";
        String password = "badpassword";
        String jwt = "bXl1c2VyaXNub3dhdXRoZW50aWNhdGVk";
        when(auth.authenticateUser(email, password)).thenThrow(BadCredentialsException.class);
        assertThrows(BadCredentialsException.class,
                ()-> userServicePort.authUser(email, password));
    }


    @Test
    public void savesCorrectly() {
        User u1 = UserData.NON_INSERTED_USER_002;
        when(userValidator.emailChecker(u1.getEmail())).thenReturn(true); //The email is valid
        when(userValidator.phoneChecker(u1.getPhone())).thenReturn(true); //The phone is valid
        assertDoesNotThrow(
                ()->userServicePort.saveUser(u1)
        ); //Insert the user
    }

    @Test
    public void savesAUserWithBadEmail() {
        User user = UserData.USER_WITH_INCORRECT_DATA;
        when(userValidator.emailChecker(user.getEmail())).thenReturn(false);
        assertThrows(IncorrectDataException.class,
                ()->userServicePort.saveUser(user));
    }

    @Test
    public void savesAUserWithBadPhone() {
        User user = UserData.USER_WITH_INCORRECT_DATA;
        when(userValidator.phoneChecker(user.getPhone())).thenReturn(false);
        assertThrows(IncorrectDataException.class,
                ()->userServicePort.saveUser(user)); //Insert the user, but throws an exception
    }

    @Test
    public void savesAUserWithAnExistingId() {
        User u1 = UserData.USER_001; //This user is inserted
        doThrow(UserConflictForIdException.class)
                .when(persistencePort).saveUser(UserData.USER_001);
        when(userValidator.emailChecker(u1.getEmail())).thenReturn(true); //The email is valid
        when(userValidator.phoneChecker(u1.getPhone())).thenReturn(true); //The phone is valid
        assertThrows(UserConflictForIdException.class,
                ()->userServicePort.saveUser(u1)); //Insert the user, but throws an exception

    }

    @Test
    public void savesAUserWithAnExistingEmail() {
        User u1 = UserData.USER_001; //This user is inserted
        doThrow(UserConflictForEmailException.class)
                .when(persistencePort).saveUser(UserData.USER_001);
        when(userValidator.emailChecker(u1.getEmail())).thenReturn(true); //The email is valid
        when(userValidator.phoneChecker(u1.getPhone())).thenReturn(true); //The phone is valid
        assertThrows(UserConflictForEmailException.class,
                ()->userServicePort.saveUser(u1)); //Insert the user, but throws an exception

    }
}