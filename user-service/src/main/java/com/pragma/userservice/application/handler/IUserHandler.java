package com.pragma.userservice.application.handler;

import com.pragma.userservice.application.dto.AuthResponse;
import com.pragma.userservice.application.dto.UserDto;
import com.pragma.userservice.application.dto.UserLoginDto;
import com.pragma.userservice.application.dto.UserRegister;

/**
 * Handler interface to communicate UserService and UserController.
 */
public interface IUserHandler {
    /**
     * Gets a userDTO that has the id provided to expose to API.
     * @param id the id of the user searched.
     * @return the UserDTO with the User's data with the id provided.
     */
    UserDto getUserByPersonalId(Long id);

    /**
     * Gets a userDTO that has the id provided to expose to API.
     * @param id the id of the user searched.
     * @return the UserDTO with the User's data with the id provided.
     */
    UserDto getUserById(Long id);
    /**
     * Gets a userDTO that has the email provided to expose to API.
     * @param email the email of the user searched.
     * @return the UserDTO with the User's data with the id provided.
     */
    UserDto getUserByEmail(String email);

    /**
     * Receives the credentials of a user to authenticate in the application.
     * @param userLoginDto the information to the user to be authenticated.
     * @return the AuthResponse with the auth data in a JWT.
     */
    AuthResponse authUser(UserLoginDto userLoginDto);
    /**
     * Saves the data of an owner in the application.
     * @param userRegister the DTO with the data of the user to register.
     */
    void saveOwner(UserRegister userRegister);

    /**
     * Saves the data of an employee in the application.
     * @param userRegister the DTO with the data of the user to register.
     */
    void saveEmployee(UserRegister userRegister);

    /**
     * Saves the data of a client in the application.
     * @param userRegister the DTO with the data of the user to register.
     */
    void saveClient(UserRegister userRegister);


}
