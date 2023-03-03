package com.pragma.foodcourtservice.domain.api;

import com.pragma.foodcourtservice.UserData;
import com.pragma.foodcourtservice.domain.model.User;
import com.pragma.foodcourtservice.infrastructure.driven_adapter.PersistentLoggedUser;
import com.pragma.foodcourtservice.infrastructure.exception.NotLoggedUserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IPersistentLoggedUserTest {
    IPersistentLoggedUser persistentLoggedUser;

    @BeforeEach
    void setUp(){
        this.persistentLoggedUser = new PersistentLoggedUser();
    }
    @Test
    void setAndGetLoggedUser() {
        User user = UserData.CLIENT;
        persistentLoggedUser.setLoggedUser(user);
        assertEquals(user, persistentLoggedUser.getLoggedUser());
    }

    @Test
    void setAndGetLoggedUserButUserIsNull() {
        User user = null;
        persistentLoggedUser.setLoggedUser(user);
        assertThrows(
                NotLoggedUserException.class,
                ()->persistentLoggedUser.getLoggedUser()
        );
    }
}