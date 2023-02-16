package com.pragma.userservice.application.handler;

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
    UserDto getUser(Long id);
    UserDto authUser(UserLoginDto userLoginDto);
    void saveUser(UserDto personaModel);
    /**
     * Saves the data of an owner in the application.
     * @param userRegister the DTO with the data of the owner to register.
     */
    void saveOwner(UserRegister userRegister);
}
