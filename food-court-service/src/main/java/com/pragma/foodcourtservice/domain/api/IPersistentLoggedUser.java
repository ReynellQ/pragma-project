package com.pragma.foodcourtservice.domain.api;

import com.pragma.foodcourtservice.domain.model.User;

public interface IPersistentLoggedUser {
    User getLoggedUser();
    void setLoggedUser(User user);
}
