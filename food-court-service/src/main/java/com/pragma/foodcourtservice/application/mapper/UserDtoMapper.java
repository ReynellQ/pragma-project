package com.pragma.foodcourtservice.application.mapper;


import com.pragma.foodcourtservice.application.dto.UserDto;
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
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserDtoMapper {


    @Mapping(source = "role.id", target = "idRole")
    User toUser(UserDto dto);
}
