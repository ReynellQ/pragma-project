package com.pragma.foodcourtservice.infrastructure.output.jpa.mapper;


import com.pragma.foodcourtservice.domain.model.FoodPlate;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.FoodPlateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapping interface to map:
 *
 * - FoodPlateEntity to FoodPlate and vice-versa
 */
@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface FoodPlateEntityMapper {
    FoodPlateEntity toEntity(FoodPlate plato);
    FoodPlate toFoodPlate(FoodPlateEntity platoEntity);
}
