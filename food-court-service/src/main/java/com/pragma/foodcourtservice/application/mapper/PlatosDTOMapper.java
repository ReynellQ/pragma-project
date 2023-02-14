package com.pragma.foodcourtservice.application.mapper;

import com.pragma.foodcourtservice.application.dto.PlatosSaveDTO;
import com.pragma.foodcourtservice.application.dto.PlatosUpdateDTO;
import com.pragma.foodcourtservice.domain.model.Platos;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PlatosDTOMapper {
    Platos toPlatoFromSave(PlatosSaveDTO platosSaveDTO);
    Platos toPlatoFromUpdate(PlatosUpdateDTO platosUpdateDTO);
}
