package com.pragma.userservice.application.mapper;

import com.pragma.userservice.application.dto.RolesDTO;
import com.pragma.userservice.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RolesDTOMapper {
    RolesDTO toDTO(Role roleModel);
}
