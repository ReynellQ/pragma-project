package com.pragma.userservice.application.mapper;

import com.pragma.userservice.application.dto.UserDto;
import com.pragma.userservice.application.dto.UserRegister;
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
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserDTOMapper {
    UserDto toDTO(User userModel);
    @Mappings({
            @Mapping(target = "idRole", source = "idRole"),
    })
    UserDto toRegister(UserRegister newPersona, Integer idRole);
    @InheritInverseConfiguration(name = "toDTO")
    User toUser(UserDto dto);
}
