package com.pragma.foodcourtservice.application.mapper;

import com.pragma.foodcourtservice.application.dto.RestauranteDTO;
import com.pragma.foodcourtservice.domain.model.Restaurantes;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RestauranteDTOMapper {

    RestauranteDTO toDTO(Restaurantes restaurantesModel);
    @InheritInverseConfiguration(name = "toDTO")
    Restaurantes toRestaurante(RestauranteDTO dto);
}
