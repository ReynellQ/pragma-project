package com.pragma.userservice.domain.spi;

import com.pragma.userservice.domain.model.User;

/**
 * Interface port that defines the methods to communicate with the persistence layer in the domain layer.
 */
public interface IUserPersistencePort {
    /**
     * Gets a user that has the id provided.
     * @param id the id of the user searched.
     * @return the User with the id provided.
     */
    User getUser(Long id);
    /**
     * Gets a user that has the email provided. It's assumed that aren't more the one user with the same email.
     * @param email the email of the user to search.
     * @return the User with the provided email.
     */
    User getUserByEmail(String email);
    /**
     * Saves a user in the persistence layer.
     * @param userModel the user to the saved.
     */
    void saveUser(User userModel);
}
