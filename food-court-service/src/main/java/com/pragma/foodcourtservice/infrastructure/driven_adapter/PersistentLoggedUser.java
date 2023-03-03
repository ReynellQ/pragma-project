package com.pragma.foodcourtservice.infrastructure.driven_adapter;

import com.pragma.foodcourtservice.domain.api.IPersistentLoggedUser;
import com.pragma.foodcourtservice.domain.model.Role;
import com.pragma.foodcourtservice.domain.model.User;
import com.pragma.foodcourtservice.infrastructure.exception.NotLoggedUserException;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PersistentLoggedUser implements IPersistentLoggedUser {
    private User user;
    private Role role;
    @Override
    public User getLoggedUser() {
        if(user == null){
            throw new NotLoggedUserException();
        }
        return user;
    }



    @Override
    public void setLoggedUser(User user) {
        this.user = user;
    }

}
