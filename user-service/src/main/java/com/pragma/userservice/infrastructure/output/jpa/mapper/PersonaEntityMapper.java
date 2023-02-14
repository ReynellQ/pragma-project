package com.pragma.userservice.infrastructure.output.jpa.mapper;

import com.pragma.userservice.domain.model.Persona;
import com.pragma.userservice.infrastructure.output.jpa.entity.PersonaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PersonaEntityMapper {
    PersonaEntity toEntity(Persona persona);
    Persona toPersona(PersonaEntity personaEntity);

}
