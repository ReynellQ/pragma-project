package com.pragma.foodcourtservice.infrastructure.driven_adapter;

import com.pragma.foodcourtservice.application.dto.users.UserDto;
import com.pragma.foodcourtservice.application.mapper.RolesDTOMapper;
import com.pragma.foodcourtservice.application.mapper.UserDtoMapper;
import com.pragma.foodcourtservice.domain.model.Role;
import com.pragma.foodcourtservice.domain.model.User;
import com.pragma.foodcourtservice.domain.spi.IUserMicroServiceClientPort;
import com.pragma.foodcourtservice.infrastructure.exception.UserNotFoundException;

/**
 * The implementation of the IUserMicroServiceClientPort interface. Defines the adapter of the SPI needed to
 * communicate with the User microservice. Implemented with Feign Client and Eureka Discover.
 */
public class UserMicroServiceClientAdapter implements IUserMicroServiceClientPort {
    public static String jwt = "xd";
    private final UserFeignClientRest userServiceConnection;
    private final UserDtoMapper userDtoMapper;
    private final RolesDTOMapper rolesDTOMapper;

    public UserMicroServiceClientAdapter(UserFeignClientRest userServiceConnection, UserDtoMapper userDtoMapper, RolesDTOMapper rolesDTOMapper) {
        this.userServiceConnection = userServiceConnection;
        this.userDtoMapper = userDtoMapper;
        this.rolesDTOMapper = rolesDTOMapper;
    }
    /**
     * Gets a user with the provided id.
     * @param personalId the id of the user.
     * @return the User with that id.
     * @throws UserNotFoundException if the user doesn't be in the user microservice.
     */
    @Override
    public User getUserByPersonalId(Long personalId) {
        User response;
        try{
            UserDto userDto = userServiceConnection.getUserByPersonalId(personalId);
            response = userDtoMapper.toUser(userDto);
        }catch(Exception ex){
            throw new UserNotFoundException();
        }
        return response;
    }

    /**
     * Gets a user with the provided email;
     *
     * @param email the email of the user
     * @return the User with that email.
     */
    @Override
    public User getUserByEmail(String email) {
        User response;
        try{
            UserDto userDto = userServiceConnection.getUserByEmail(email);
            response = userDtoMapper.toUser(userDto);
        }catch(Exception ex){
            throw new UserNotFoundException();
        }
        return response;
    }

    /**
     * Gets the user's role with the provided id.
     *
     * @param personalId the id of the user.
     * @return the role of the User with that id.
     */
    @Override
    public Role getRolesUser(Long personalId) {
        Role response;
        try{
            UserDto userDto = userServiceConnection.getUserByPersonalId(personalId);
            response = rolesDTOMapper.toRole(userDto.getRole());
        }catch(Exception ex){
            throw new UserNotFoundException();
        }
        return response;
    }
}
