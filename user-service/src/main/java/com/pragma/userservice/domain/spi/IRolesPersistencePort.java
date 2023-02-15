package com.pragma.userservice.domain.spi;

import com.pragma.userservice.domain.model.Role;

public interface IRolesPersistencePort {
    Role getRol(Integer id);
}
