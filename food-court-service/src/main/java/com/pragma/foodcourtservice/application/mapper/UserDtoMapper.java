package com.pragma.foodcourtservice.application.mapper;

import com.pragma.foodcourtservice.application.dto.users.UserDto;
import com.pragma.foodcourtservice.domain.model.Role;
import com.pragma.foodcourtservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

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
        uses = {RolesDtoMapper.class}
)
public interface UserDtoMapper {
    @Mapping(source = "userModel.id", target = "id")
    @Mapping(source = "userModel.name", target = "name")
    @Mapping(source = "role", target = "role")
    UserDto toDTO(User userModel, Role role);

    @Mapping(source = "dto.role.id", target = "idRole")
    User toUser(UserDto dto);
}
