package com.pragma.userservice.application.mapper;

import com.pragma.userservice.application.dto.RoleDto;
import com.pragma.userservice.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RolesDTOMapper {
    RoleDto toDTO(Role roleModel);
}
