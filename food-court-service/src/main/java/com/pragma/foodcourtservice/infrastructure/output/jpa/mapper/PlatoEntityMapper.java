package com.pragma.foodcourtservice.infrastructure.output.jpa.mapper;


import com.pragma.foodcourtservice.domain.model.Platos;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.PlatosEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PlatoEntityMapper {
    PlatosEntity toEntity(Platos plato);
    Platos toPlato(PlatosEntity platoEntity);
}
