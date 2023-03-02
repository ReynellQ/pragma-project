package com.pragma.userservice.domain.spi;

import com.pragma.userservice.application.dto.RoleDto;
import com.pragma.userservice.domain.model.ROLES;
import com.pragma.userservice.domain.model.Role;
import com.pragma.userservice.infrastructure.exception.InvalidRoleException;
import com.pragma.userservice.infrastructure.exception.UserNotFoundException;
import com.pragma.userservice.infrastructure.output.jpa.adapter.RoleJpaAdapter;
import com.pragma.userservice.infrastructure.output.jpa.entity.RolesEntity;
import com.pragma.userservice.infrastructure.output.jpa.mapper.RolesEntityMapper;
import com.pragma.userservice.infrastructure.output.jpa.repository.IRolesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IRolesPersistencePortTest {
    IRolesRepository rolesRepository;
    RolesEntityMapper rolesEntityMapper;
    IRolesPersistencePort rolesPersistencePort;
    @BeforeEach
    void setUp(){
        rolesRepository = mock(IRolesRepository.class);
        rolesEntityMapper = mock(RolesEntityMapper.class);
        rolesPersistencePort = new RoleJpaAdapter(rolesRepository, rolesEntityMapper);
    }

    @Test
    void getExistingRole() {
        Role role = new Role(ROLES.ADMIN.id, "ADMIN", "ROL DE ADMIN");
        RolesEntity rolesEntity = new RolesEntity(ROLES.ADMIN.id, "ADMIN", "ROL DE ADMIN");
        when(rolesEntityMapper.toRol(rolesEntity)).thenReturn(role);
        when(rolesRepository.findById(rolesEntity.getId())).thenReturn(Optional.of(rolesEntity));
        assertEquals( role, rolesPersistencePort.getRol(role.getId()));
    }

    @Test
    void getNonExistingRole() {
        Role role = new Role(-1, "ROL INEXISTENTE", "ESTE ROL NO EXISTE");
        when(rolesRepository.findById(role.getId())).thenReturn(Optional.empty());
        assertThrows(
                InvalidRoleException.class,
                ()-> rolesPersistencePort.getRol(role.getId())
        );
    }
}