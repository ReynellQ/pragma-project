package com.pragma.foodcourtservice.domain.spi;

import com.pragma.foodcourtservice.domain.model.Role;
import com.pragma.foodcourtservice.domain.model.User;

/**
 * Interface that defines the port of the SPI needed to communicate with the User microservice.
 */
public interface IUserMicroServiceClientPort {
    /**
     * Gets a user with the provided id.
     * @param personalId the id of the user.
     * @return the User with that id.
     */
    User getUserByPersonalId(Long personalId);

    /**
     * Gets a user with the provided email;
     * @param email the email of the user
     * @return the User with that email.
     */
    User getUserByEmail(String email);

    /**
     * Gets the user's role with the provided personal id.
     * @param personalId the id of the user.
     * @return the role of the User with that personal id.
     */
    Role getRolesUser(Long personalId);
}
