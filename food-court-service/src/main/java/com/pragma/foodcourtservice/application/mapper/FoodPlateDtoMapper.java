package com.pragma.foodcourtservice.application.mapper;

import com.pragma.foodcourtservice.application.dto.FoodPlateRegisterDto;
import com.pragma.foodcourtservice.application.dto.FoodPlateUpdateDto;
import com.pragma.foodcourtservice.domain.model.FoodPlate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapping interface to map:
 *
 * - FoodPlateRegisterDTO to FoodPlate
 * - FoodPlateUpdateDto to FoodPlate
 */
@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface FoodPlateDtoMapper {
    FoodPlate toPlatoFromSave(FoodPlateRegisterDto foodPlateRegisterDTO);
    FoodPlate toPlatoFromUpdate(FoodPlateUpdateDto foodPlateUpdateDTO);
}
