package com.pragma.foodcourtservice.infrastructure.microservices.adapters;

import com.pragma.foodcourtservice.application.dto.users.UserDto;
import com.pragma.foodcourtservice.application.mapper.RolesDTOMapper;
import com.pragma.foodcourtservice.application.mapper.UserDtoMapper;
import com.pragma.foodcourtservice.domain.model.Role;
import com.pragma.foodcourtservice.domain.model.User;
import com.pragma.foodcourtservice.domain.spi.IUserMicroServiceClientPort;
import com.pragma.foodcourtservice.infrastructure.microservices.feign_client.UserFeignClientRest;
import com.pragma.foodcourtservice.infrastructure.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

/**
 * The implementation of the IUserMicroServiceClientPort interface. Defines the adapter of the SPI needed to
 * communicate with the User microservice. Implemented with Feign Client and Eureka Discover.
 */
@RequiredArgsConstructor
public class UserMicroServiceClientAdapter implements IUserMicroServiceClientPort {
    private final UserFeignClientRest userServiceConnection;
    private final UserDtoMapper userDtoMapper;
    private final RolesDTOMapper rolesDTOMapper;

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
     * Gets a user with the provided id.
     * @param id the id of the user.
     * @return the User with that id.
     * @throws UserNotFoundException if the user doesn't be in the user microservice.
     */
    @Override
    public User getUserById(Long id) {
        User response;
        try{
            UserDto userDto = userServiceConnection.getUserById(id);
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
     * @param id the id of the user.
     * @return the role of the User with that id.
     */
    @Override
    public Role getRolesUser(Long id) {
        Role response;
        try{
            UserDto userDto = userServiceConnection.getUserById(id);
            response = rolesDTOMapper.toRole(userDto.getRole());
        }catch(Exception ex){
            throw new UserNotFoundException();
        }
        return response;
    }

    /**
     * Saves an employee in the user microservice.
     *
     * @param employee the user of role "employee" to be saved.
     */
    @Override
    public void saveAnEmployee(User employee) {
        userServiceConnection.saveAnEmployee(employee);
    }
}
