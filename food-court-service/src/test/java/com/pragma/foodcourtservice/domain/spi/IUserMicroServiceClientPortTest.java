package com.pragma.foodcourtservice.domain.spi;

import com.pragma.foodcourtservice.RestaurantData;
import com.pragma.foodcourtservice.UserData;
import com.pragma.foodcourtservice.application.dto.users.RoleDto;
import com.pragma.foodcourtservice.application.dto.users.UserDto;
import com.pragma.foodcourtservice.application.mapper.RolesDtoMapper;
import com.pragma.foodcourtservice.application.mapper.UserDtoMapper;
import com.pragma.foodcourtservice.domain.model.ROLES;
import com.pragma.foodcourtservice.domain.model.Role;
import com.pragma.foodcourtservice.domain.model.User;
import com.pragma.foodcourtservice.infrastructure.exception.UserNotFoundException;
import com.pragma.foodcourtservice.infrastructure.microservices.adapters.UserMicroServiceClientAdapter;
import com.pragma.foodcourtservice.infrastructure.microservices.feign_client.UserFeignClientRest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IUserMicroServiceClientPortTest {
    UserFeignClientRest userServiceConnection;
    UserDtoMapper userDtoMapper;
    RolesDtoMapper rolesDTOMapper;

    IUserMicroServiceClientPort userMicroServiceClientPort;

    @BeforeEach
    void setUp(){
        userServiceConnection = mock(UserFeignClientRest.class);
        userDtoMapper = mock(UserDtoMapper.class);
        rolesDTOMapper = mock(RolesDtoMapper.class);

        userMicroServiceClientPort = new UserMicroServiceClientAdapter(userServiceConnection, userDtoMapper,
                rolesDTOMapper);
    }
    @Test
    void getUserByPersonalIdCorrectly() {
        User user = UserData.CLIENT;
        RoleDto roleDto = new RoleDto(ROLES.CLIENT, "Cliente", "Esto es un cliente");
        UserDto userDto = new UserDto(user.getId(), user.getPersonalId(), user.getName(), user.getLastname(),
                user.getPhone(), user.getEmail(), user.getPassword(),
                roleDto);
        when(userServiceConnection.getUserByPersonalId(user.getPersonalId()))
                .thenReturn(userDto);
        when(userDtoMapper.toUser(userDto))
                .thenReturn(user);
        assertEquals(
                user,
                userMicroServiceClientPort.getUserByPersonalId(user.getPersonalId())
        );

    }

    @Test
    void getUserByPersonalIdButOccurAnErrorCallingClient() {
        User user = UserData.CLIENT;
        RoleDto roleDto = new RoleDto(ROLES.CLIENT, "Cliente", "Esto es un cliente");
        UserDto userDto = new UserDto(user.getId(), user.getPersonalId(), user.getName(), user.getLastname(),
                user.getPhone(), user.getEmail(), user.getPassword(),
                roleDto);
        when(userServiceConnection.getUserByPersonalId(user.getPersonalId()))
                .thenThrow(RuntimeException.class);
        when(userDtoMapper.toUser(userDto))
                .thenReturn(user);
        assertThrows(
                UserNotFoundException.class,
                ()->userMicroServiceClientPort.getUserByPersonalId(user.getPersonalId())
        );

    }

    @Test
    void getUserByIdCorrectly() {
        User user = UserData.CLIENT;
        RoleDto roleDto = new RoleDto(ROLES.CLIENT, "Cliente", "Esto es un cliente");
        UserDto userDto = new UserDto(user.getId(), user.getPersonalId(), user.getName(), user.getLastname(),
                user.getPhone(), user.getEmail(), user.getPassword(),
                roleDto);
        when(userServiceConnection.getUserById(user.getId()))
                .thenReturn(userDto);
        when(userDtoMapper.toUser(userDto))
                .thenReturn(user);
        assertEquals(
                user,
                userMicroServiceClientPort.getUserById(user.getId())
        );
    }

    @Test
    void getUserByIdButOccurAnErrorCallingClient() {
        User user = UserData.CLIENT;
        RoleDto roleDto = new RoleDto(ROLES.CLIENT, "Cliente", "Esto es un cliente");
        UserDto userDto = new UserDto(user.getId(), user.getPersonalId(), user.getName(), user.getLastname(),
                user.getPhone(), user.getEmail(), user.getPassword(),
                roleDto);
        when(userServiceConnection.getUserById(user.getId()))
                .thenThrow(RuntimeException.class);
        when(userDtoMapper.toUser(userDto))
                .thenReturn(user);
        assertThrows(
                UserNotFoundException.class,
                ()->userMicroServiceClientPort.getUserById(user.getId())
        );
    }

    @Test
    void getUserByEmailCorrectly() {
        User user = UserData.CLIENT;
        RoleDto roleDto = new RoleDto(ROLES.CLIENT, "Cliente", "Esto es un cliente");
        UserDto userDto = new UserDto(user.getId(), user.getPersonalId(), user.getName(), user.getLastname(),
                user.getPhone(), user.getEmail(), user.getPassword(),
                roleDto);
        when(userServiceConnection.getUserByEmail(user.getEmail()))
                .thenReturn(userDto);
        when(userDtoMapper.toUser(userDto))
                .thenReturn(user);
        assertEquals(
                user,
                userMicroServiceClientPort.getUserByEmail(user.getEmail())
        );
    }

    @Test
    void getUserByEmailButOccurAnErrorCallingClient() {
        User user = UserData.CLIENT;
        RoleDto roleDto = new RoleDto(ROLES.CLIENT, "Cliente", "Esto es un cliente");
        UserDto userDto = new UserDto(user.getId(), user.getPersonalId(), user.getName(), user.getLastname(),
                user.getPhone(), user.getEmail(), user.getPassword(),
                roleDto);
        when(userServiceConnection.getUserByEmail(user.getEmail()))
                .thenThrow(RuntimeException.class);
        when(userDtoMapper.toUser(userDto))
                .thenReturn(user);
        assertThrows(
                UserNotFoundException.class,
                ()->userMicroServiceClientPort.getUserByEmail(user.getEmail())
        );
    }

    @Test
    void getRolesUserCorrectly() {
        User user = UserData.CLIENT;
        RoleDto roleDto = new RoleDto(ROLES.CLIENT, "Cliente", "Esto es un cliente");
        Role role = new Role(ROLES.CLIENT, "Cliente", "Esto es un cliente");
        UserDto userDto = new UserDto(user.getId(), user.getPersonalId(), user.getName(), user.getLastname(),
                user.getPhone(), user.getEmail(), user.getPassword(),
                roleDto);
        when(userServiceConnection.getUserByPersonalId(user.getPersonalId()))
                .thenReturn(userDto);
        when(rolesDTOMapper.toRole(roleDto))
                .thenReturn(role);
        assertEquals(
                role,
                userMicroServiceClientPort.getRolesUser(user.getPersonalId())
        );
    }

    @Test
    void getRolesUserButOccurAnErrorCallingClient() {
        User user = UserData.CLIENT;
        RoleDto roleDto = new RoleDto(ROLES.CLIENT, "Cliente", "Esto es un cliente");
        Role role = new Role(ROLES.CLIENT, "Cliente", "Esto es un cliente");
        UserDto userDto = new UserDto(user.getId(), user.getPersonalId(), user.getName(), user.getLastname(),
                user.getPhone(), user.getEmail(), user.getPassword(),
                roleDto);
        when(userServiceConnection.getUserById(user.getId()))
                .thenThrow(RuntimeException.class);
        assertThrows(
                UserNotFoundException.class,
                ()->userMicroServiceClientPort.getRolesUser(user.getId())
        );
    }

    @Test
    void saveAnEmployee() {
        User employee = RestaurantData.EMPLOYEE;
        assertDoesNotThrow(
                ()-> userMicroServiceClientPort.saveAnEmployee(employee)
        );
    }
}