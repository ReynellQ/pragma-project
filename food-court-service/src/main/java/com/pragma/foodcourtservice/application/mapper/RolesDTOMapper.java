package com.pragma.foodcourtservice.application.mapper;

import com.pragma.foodcourtservice.application.dto.users.RoleDto;
import com.pragma.foodcourtservice.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RolesDTOMapper {
    RoleDto toDTO(Role roleModel);
    Role toRole(RoleDto roleDto);
}
