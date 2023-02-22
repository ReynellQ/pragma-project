package com.pragma.userservice.domain.useCase;

import com.pragma.userservice.domain.api.IAuth;
import com.pragma.userservice.domain.api.IUserValidator;
import com.pragma.userservice.domain.api.IUserServicePort;
import com.pragma.userservice.domain.exception.IncorrectDataException;
import com.pragma.userservice.domain.exception.IncorrectCredentialsException;
import com.pragma.userservice.domain.model.User;
import com.pragma.userservice.domain.spi.IRolesPersistencePort;
import com.pragma.userservice.domain.spi.IUserPersistencePort;
import lombok.RequiredArgsConstructor;

/**
 * Class UserUseCase that implements the interface IUserServicePort, and defines the business logic that be used by the
 * API.
 */
@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort personaPersistencePort;
    private final IRolesPersistencePort rolesPersistencePort;
    private final IUserValidator personaChecker;
    private final IAuth auth;

    /**
     * Gets a user that has the id provided, searching it in the persistence layer.
     * @param personalId the id of the user searched.
     * @return the User with the id provided.
     */
    @Override
    public User getUserByPersonalId(Long personalId) {
        return personaPersistencePort.getUserByPersonalId(personalId);
    }

    /**
     * Gets a user that has the email provided.
     *
     * @param email the email of the user searched.
     * @return the User with the email provided.
     */
    @Override
    public User getUserByEmail(String email) {
        return personaPersistencePort.getUserByEmail(email);
    }


    /**
     * Authenticates a user with the email and password provided, calling the IAuth service in order to authenticate
     * the user.
     * @param email the email of the user.
     * @param password the password of the user.
     * @return the String that contains the JWT of the authenticated user.
     */
    @Override
    public String authUser(String email, String password) {
        return auth.authenticateUser(email, password);
    }

    /**
     * Saves a user in the application, checking first their data. It checks the email and the phone. Throws an
     * exception if the data doesn't pass the validation.
     * @param userModel the user to the saved.
     * @throws IncorrectDataException
     */
    @Override
    public void saveUser(User userModel) {
        //Verificar rol
        rolesPersistencePort.getRol(userModel.getIdRole());
        //Verificar correo
        if(!personaChecker.emailChecker(userModel.getEmail()))
            throw new IncorrectDataException();
        //Verificar número de telefono
        if(!personaChecker.phoneChecker(userModel.getPhone()))
            throw new IncorrectDataException();
        //Encriptar contraseña antes de guardar en el puerto de persistencia
        userModel.setPassword(auth.encryptPassword(userModel.getPassword()));
        personaPersistencePort.saveUser(userModel);
    }
}
