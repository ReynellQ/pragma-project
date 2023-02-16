package com.pragma.foodcourtservice.infrastructure.thirdparty;

import com.pragma.foodcourtservice.application.dto.UserDto;
import com.pragma.foodcourtservice.application.mapper.UserDtoMapper;
import com.pragma.foodcourtservice.domain.model.User;
import com.pragma.foodcourtservice.domain.spi.IUserMicroServiceClientPort;
import com.pragma.foodcourtservice.infrastructure.exception.UserDoesntExistsException;
import org.springframework.web.client.RestTemplate;
/**
 * The implementation of the IUserMicroServiceClientPort interface. Defines the adapter of the SPI needed to
 * communicate with the User microservice. Implemented with Feign Client and Eureka Discover.
 */
public class UserMicroServiceClientAdapter implements IUserMicroServiceClientPort {
    private final RestTemplate userServiceConnection;
    private final UserDtoMapper userDtoMapper;

    public UserMicroServiceClientAdapter(RestTemplate userServiceConnection, UserDtoMapper userDtoMapper) {
        this.userServiceConnection = userServiceConnection;
        this.userDtoMapper = userDtoMapper;
    }
    /**
     * Gets a user with the provided id.
     * @param id the id of the user.
     * @return the User with that id.
     * @throws UserDoesntExistsException if the user doesn't be in the user microservice.
     */
    @Override
    public User getPersona(Long id) {
        User response;
        try{
            UserDto userDto = userServiceConnection.getForObject("http://localhost:8080/user/"+id, UserDto.class);
            response = userDtoMapper.toUser(userDto);
        }catch(Exception ex){
            throw new UserDoesntExistsException();
        }
        return response;
    }
}
