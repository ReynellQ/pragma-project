package com.pragma.userservice.infrastructure.output.jpa.adapter;

import com.pragma.userservice.domain.model.Roles;
import com.pragma.userservice.domain.spi.IRolesPersistencePort;
import com.pragma.userservice.infrastructure.exception.InvalidRol;
import com.pragma.userservice.infrastructure.output.jpa.entity.RolesEntity;
import com.pragma.userservice.infrastructure.output.jpa.mapper.RolesEntityMapper;
import com.pragma.userservice.infrastructure.output.jpa.repository.IRolesRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RolesJpaAdapter implements IRolesPersistencePort {
    private final RolesEntityMapper rolesEntityMapper;
    private final IRolesRepository rolesRepository;

    @Override
    public Roles getRol(Integer id) {
        Optional<RolesEntity> rol = rolesRepository.findById(id);
        if(rol.isEmpty())
            throw new InvalidRol();
        return rolesEntityMapper.toRol(rol.get());
    }
}
