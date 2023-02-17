package com.pragma.foodcourtservice.infrastructure.driven_adapter;

import com.pragma.foodcourtservice.application.dto.UserDto;
import com.pragma.foodcourtservice.application.mapper.UserDtoMapper;
import com.pragma.foodcourtservice.domain.model.User;
import com.pragma.foodcourtservice.domain.spi.IUserMicroServiceClientPort;
import com.pragma.foodcourtservice.infrastructure.exception.UserNotFoundException;

/**
 * The implementation of the IUserMicroServiceClientPort interface. Defines the adapter of the SPI needed to
 * communicate with the User microservice. Implemented with Feign Client and Eureka Discover.
 */
public class UserMicroServiceClientAdapter implements IUserMicroServiceClientPort {
    private final UserFeignClientRest userServiceConnection;
    private final UserDtoMapper userDtoMapper;

    public UserMicroServiceClientAdapter(UserFeignClientRest userServiceConnection, UserDtoMapper userDtoMapper) {
        this.userServiceConnection = userServiceConnection;
        this.userDtoMapper = userDtoMapper;
    }
    /**
     * Gets a user with the provided id.
     * @param id the id of the user.
     * @return the User with that id.
     * @throws UserNotFoundException if the user doesn't be in the user microservice.
     */
    @Override
    public User getUser(Long id) {
        User response;
        try{
            UserDto userDto = userServiceConnection.getUser(id);
            response = userDtoMapper.toUser(userDto);
        }catch(Exception ex){
            throw new UserNotFoundException();
        }
        return response;
    }
}
