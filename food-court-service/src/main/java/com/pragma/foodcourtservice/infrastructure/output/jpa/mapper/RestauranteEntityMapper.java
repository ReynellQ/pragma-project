package com.pragma.foodcourtservice.infrastructure.output.jpa.mapper;

import com.pragma.foodcourtservice.domain.model.Restaurantes;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.RestaurantesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RestauranteEntityMapper {
    RestaurantesEntity toEntity(Restaurantes restaurantes);
    Restaurantes toRestaurante(RestaurantesEntity personaEntity);

}
