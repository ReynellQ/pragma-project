package com.pragma.foodcourtservice.domain.api;

import com.pragma.foodcourtservice.domain.model.Role;
import com.pragma.foodcourtservice.domain.model.User;

public interface IPersistentLoggedUser {
    User getLoggedUser();
    Role getRoleOfLoggedUser();
    void setLoggedUser(User user);
    void setRoleOfLoggedUser(Role role);
}
