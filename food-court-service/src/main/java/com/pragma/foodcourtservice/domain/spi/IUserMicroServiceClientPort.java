package com.pragma.foodcourtservice.domain.spi;

import com.pragma.foodcourtservice.domain.model.User;

/**
 * Interface that defines the port of the SPI needed to communicate with the User microservice.
 */
public interface IUserMicroServiceClientPort {
    /**
     * Gets a user with the provided id.
     * @param id the id of the user.
     * @return the User with that id.
     */
    User getUser(Long id);
}
