package com.pragma.userservice.application.mapper;

import com.pragma.userservice.application.dto.PersonaDTO;
import com.pragma.userservice.application.dto.PersonaRegister;
import com.pragma.userservice.domain.model.Persona;
import com.pragma.userservice.domain.model.Roles;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PersonaDTOMapper {
    @Mappings({
            @Mapping(target = "rol", source = "personaModel.idRol"),
    })
    PersonaDTO toDTO(Persona personaModel);

    PersonaDTO toRegister(PersonaRegister newPersona);
    @InheritInverseConfiguration(name = "toDTO")
    Persona toPersona(PersonaDTO dto);
}
