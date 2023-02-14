package com.pragma.userservice.application.mapper;

import com.pragma.userservice.application.dto.PersonaDTO;
import com.pragma.userservice.application.dto.PersonaRegister;
import com.pragma.userservice.domain.model.Persona;
import com.pragma.userservice.domain.model.Roles;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {RolesDTOMapper.class}
)
public interface PersonaDTOMapper {
    @Mappings({
            @Mapping(target = "id", source = "personaModel.id"),
            @Mapping(target = "nombre", source = "personaModel.nombre"),
            @Mapping(target = "rol.nombre", source = "rolesModel.nombre"),
            @Mapping(target = "rol.descripcion", source = "rolesModel.descripcion")
    })
    PersonaDTO toDTO(Persona personaModel, Roles rolesModel);

    PersonaDTO toRegister(PersonaRegister newPersona);
    @InheritInverseConfiguration(name = "toDTO")
    Persona toPersona(PersonaDTO dto);
}
