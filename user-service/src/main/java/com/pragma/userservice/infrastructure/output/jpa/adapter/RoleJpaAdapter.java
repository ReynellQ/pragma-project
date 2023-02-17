package com.pragma.userservice.infrastructure.output.jpa.adapter;

import com.pragma.userservice.domain.model.Role;
import com.pragma.userservice.domain.spi.IRolesPersistencePort;
import com.pragma.userservice.infrastructure.exception.InvalidRoleException;
import com.pragma.userservice.infrastructure.output.jpa.entity.RolesEntity;
import com.pragma.userservice.infrastructure.output.jpa.mapper.RolesEntityMapper;
import com.pragma.userservice.infrastructure.output.jpa.repository.IRolesRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RoleJpaAdapter implements IRolesPersistencePort {
    private final IRolesRepository rolesRepository;
    private final RolesEntityMapper rolesEntityMapper;
    @Override
    public Role getRol(Integer id) {
        Optional<RolesEntity> role = rolesRepository.findById(id);
        if(role.isEmpty())
            throw new InvalidRoleException();
        return rolesEntityMapper.toRol(role.get());
    }
}
