package com.pragma.userservice.infrastructure.output.jpa.mapper;

import com.pragma.userservice.domain.model.Roles;
import com.pragma.userservice.infrastructure.output.jpa.entity.RolesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RolesEntityMapper {
    RolesEntity toEntity(Roles persona);
    Roles toRol(RolesEntity personaEntity);
}
