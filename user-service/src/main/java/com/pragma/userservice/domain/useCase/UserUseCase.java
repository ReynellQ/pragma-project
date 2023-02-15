package com.pragma.userservice.domain.useCase;

import com.pragma.userservice.domain.api.IAuth;
import com.pragma.userservice.domain.api.IUserValidator;
import com.pragma.userservice.domain.api.IUserServicePort;
import com.pragma.userservice.domain.exception.IncorrectDataException;
import com.pragma.userservice.domain.exception.IncorrectCredentialsException;
import com.pragma.userservice.domain.model.User;
import com.pragma.userservice.domain.spi.IUserPersistencePort;

/**
 * Class UserUseCase that implements the interface IUserServicePort, and defines the business logic that be used by the
 * API.
 */
public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort personaPersistencePort;
    private final IUserValidator personaChecker;
    private final IAuth auth;

    public UserUseCase(IUserPersistencePort personaPersistencePort,
                       IUserValidator personaChecker, IAuth auth){
        this.personaPersistencePort = personaPersistencePort;
        this.personaChecker = personaChecker;
        this.auth = auth;
    }
    /**
     * Gets a user that has the id provided, searching it in the persistence layer.
     * @param id the id of the user searched.
     * @return the User with the id provided.
     */
    @Override
    public User getUser(Long id) {
        return personaPersistencePort.getUser(id);
    }


    /**
     * Authenticates a user with the email and password provided, comparing with the user in the persistence layer that
     * has the password ciphered, and return that user if the credentials match. Throws an exception if the credentials
     * doesn't match with some user on the database.
     * @param email the email of the user.
     * @param password the password of the user.
     * @return the User to be authenticated.
     * @throws IncorrectCredentialsException
     */
    @Override
    public User authUser(String email, String password) {
        User p = personaPersistencePort.getUserByEmail(email);
        if(!p.getPassword().equals(password))
            throw new IncorrectCredentialsException();
        return p;
    }

    /**
     * Saves an owner in the application, checking first their data. It checks the email and the phone. Throws an
     * exception if the data doesn't pass the validation.
     * @param userModel the user to the saved.
     * @throws IncorrectDataException
     */
    @Override
    public void saveOwner(User userModel) {
        //Verificar correo
        if(!personaChecker.emailChecker(userModel.getEmail()))
            throw new IncorrectDataException();
        //Verificar número de telefono
        if(!personaChecker.phoneChecker(userModel.getPhone()))
            throw new IncorrectDataException();
        //Encriptar contraseña
        userModel.setPassword(auth.encryptPassword(userModel.getPassword()));
        personaPersistencePort.saveUser(userModel);
    }
}
