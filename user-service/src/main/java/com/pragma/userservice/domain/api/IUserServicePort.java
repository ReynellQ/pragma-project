package com.pragma.userservice.domain.api;

import com.pragma.userservice.domain.model.User;
import com.pragma.userservice.domain.model.Role;

/**
 * Interface IUserServicePort that defines the port of the user service and exposes the methods to the API.
 */
public interface IUserServicePort {
    /**
     * Gets a user that has the id provided.
     * @param id the id of the user searched.
     * @return the User with the id provided.
     */
    User getUser(Long id);

    /**
     * Authenticates a user with the email and password provided and return that user.
     * @param email the email of the user.
     * @param password the password of the user.
     * @return the User to be authenticated.
     */
    User authUser(String email, String password);

    /**
     * Saves an owner in the application.
     * @param userModel the user to the saved.
     */
    void saveOwner(User userModel);
}
