package com.pragma.userservice.application.mapper;

import com.pragma.userservice.application.dto.UserDto;
import com.pragma.userservice.application.dto.UserRegister;
import com.pragma.userservice.domain.model.Role;
import com.pragma.userservice.domain.model.User;
import org.mapstruct.*;

/**
 * Mapping interface to map:
 *
 * - UserDTO to User and vice-versa
 * - UserRegister to UserDTO.
 */
@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {RolesDTOMapper.class}
)
public interface UserDTOMapper {
    @Mapping(source = "userModel.id", target = "id")
    @Mapping(source = "userModel.name", target = "name")
    @Mapping(source = "role", target = "role")
    UserDto toDTO(User userModel, Role role);

    User toRegister(UserRegister newPersona);

    User toUser(UserDto dto);
}
