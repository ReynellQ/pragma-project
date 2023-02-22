package com.pragma.userservice.application.handler;

import com.pragma.userservice.application.dto.AuthResponse;
import com.pragma.userservice.application.dto.UserDto;
import com.pragma.userservice.application.dto.UserLoginDto;
import com.pragma.userservice.application.dto.UserRegister;
import com.pragma.userservice.application.mapper.UserDTOMapper;
import com.pragma.userservice.domain.api.IUserServicePort;
import com.pragma.userservice.domain.model.ROLES;
import com.pragma.userservice.domain.model.Role;
import com.pragma.userservice.domain.model.User;
import com.pragma.userservice.domain.spi.IRolesPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
/**
 * Implementation of the handler interface to communicate UserService and UserController. This uses the user service
 * port and the mapper to UserDTO (the input of the API) to User (input of model layer).
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {
    private final IUserServicePort iUserServicePort;
    private final IRolesPersistencePort rolesPersistencePort;
    private final UserDTOMapper userDTOMapper;

    /**
     * Gets a userDTO that has the id provided to expose to API. Obtains the user by the user service and map to the
     * DTO.
     * @param id the id of the user searched.
     * @return the UserDTO with the User's data with the id provided.
     */
    @Override
    public UserDto getUserByPersonalId(Long id) {
        User p = iUserServicePort.getUserByPersonalId(id);
        Role r = rolesPersistencePort.getRol(p.getIdRole());
        UserDto dto = userDTOMapper.toDTO(p, r);
        return dto;
    }

    /**
     * Gets a userDTO that has the email provided to expose to API.
     *
     * @param email the email of the user searched.
     * @return the UserDTO with the User's data with the id provided.
     */
    @Override
    public UserDto getUserByEmail(String email) {
        User p = iUserServicePort.getUserByEmail(email);
        Role r = rolesPersistencePort.getRol(p.getIdRole());
        UserDto dto = userDTOMapper.toDTO(p, r);
        return dto;
    }

    /**
     * Receives the credentials of a user to authenticate in the application. Calls the userServicePort in order to
     * log the user.
     * @param userLoginDto the information to the user to be authenticated.
     * @return the information of the user that log in.
     */
    @Override
    public AuthResponse authUser(UserLoginDto userLoginDto) {
        return new AuthResponse(iUserServicePort.authUser(userLoginDto.getEmail(), userLoginDto.getPassword()));
    }


    /**
     * Saves the data of an owner in the application. Map the data of the owner to register and calls the service
     * in charge of save the owner.
     * @param userRegister the DTO with the data of the owner to register.
     */
    @Override
    public void saveOwner(UserRegister userRegister) {
        User newUser = userDTOMapper.toRegister(userRegister, ROLES.OWNER.id);
        iUserServicePort.saveUser(newUser);
    }

    /**
     * Saves the data of an employee in the application.
     *
     * @param userRegister the DTO with the data of the user to register.
     */
    @Override
    public void saveEmployee(UserRegister userRegister) {
        User newUser = userDTOMapper.toRegister(userRegister, ROLES.EMPLOYEE.id);
        iUserServicePort.saveUser(newUser);
    }

    /**
     * Saves the data of a client in the application. Map the data of the client to register and calls the service
     * in charge of save the owner.
     *
     * @param userRegister the DTO with the data of the user to register.
     */
    @Override
    public void saveClient(UserRegister userRegister) {
        User newUser = userDTOMapper.toRegister(userRegister, ROLES.CLIENT.id);
        iUserServicePort.saveUser(newUser);
    }
}
