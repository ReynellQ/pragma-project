package com.pragma.foodcourtservice.application.mapper;

import com.pragma.foodcourtservice.application.dto.UserDto;
import com.pragma.foodcourtservice.domain.model.User;
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
public interface UserDtoMapper {
    UserDto toDTO(User userModel);
    @InheritInverseConfiguration(name = "toDTO")
    User toUser(UserDto dto);
}
