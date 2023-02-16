package com.pragma.userservice.application.handler;

import com.pragma.userservice.application.dto.UserDto;
import com.pragma.userservice.application.dto.UserLoginDto;
import com.pragma.userservice.application.dto.UserRegister;
import com.pragma.userservice.application.mapper.UserDTOMapper;
import com.pragma.userservice.domain.api.IUserServicePort;
import com.pragma.userservice.domain.model.User;
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
    private final UserDTOMapper userDTOMapper;

    /**
     * Gets a userDTO that has the id provided to expose to API. Obtains the user by the user service and map to the
     * DTO.
     * @param id the id of the user searched.
     * @return the UserDTO with the User's data with the id provided.
     */
    @Override
    public UserDto getUser(Long id) {
        User p = iUserServicePort.getUser(id);
        return userDTOMapper.toDTO(p);
    }

    @Override
    public UserDto authUser(UserLoginDto userLoginDto) {
        User p = iUserServicePort.authUser(userLoginDto.getEmail(), userLoginDto.getPassword());
        return userDTOMapper.toDTO(p);
    }

    @Override
    public void saveUser(UserDto userDto) {
        iUserServicePort.saveOwner(userDTOMapper.toUser(userDto));
    }

    /**
     * Saves the data of an owner in the application. Map the data of the user to register and calls the service
     * in charge of save the user.
     * @param userRegister the DTO with the data of the owner to register.
     */
    @Override
    public void saveOwner(UserRegister userRegister) {
        UserDto newPersona = userDTOMapper.toRegister(userRegister, 2);
        User p = userDTOMapper.toUser(newPersona);
        iUserServicePort.saveOwner(p);
    }
}
