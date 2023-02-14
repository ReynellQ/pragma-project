package com.pragma.userservice.domain.spi;

import com.pragma.userservice.domain.model.Roles;

public interface IRolesPersistencePort {
    Roles getRol(Integer id);
}
