package com.pragma.foodcourtservice.infrastructure.driven_adapter;

import com.pragma.foodcourtservice.domain.api.IPersistentLoggedUser;
import com.pragma.foodcourtservice.domain.model.Role;
import com.pragma.foodcourtservice.domain.model.User;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PersistentLoggedUser implements IPersistentLoggedUser {
    private User user;
    private Role role;
    @Override
    public User getLoggedUser() {
        return user;
    }

    @Override
    public Role getRoleOfLoggedUser() {
        return role;
    }

    @Override
    public void setLoggedUser(User user) {
        this.user = user;
    }

    @Override
    public void setRoleOfLoggedUser(Role role) {
        this.role = role;
    }
}
