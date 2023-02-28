package com.pragma.foodcourtservice.application.mapper;

import com.pragma.foodcourtservice.application.dto.foodplate.FoodPlateChangeState;
import com.pragma.foodcourtservice.application.dto.foodplate.FoodPlateDto;
import com.pragma.foodcourtservice.application.dto.foodplate.FoodPlateRegisterDto;
import com.pragma.foodcourtservice.application.dto.foodplate.FoodPlateUpdateDto;
import com.pragma.foodcourtservice.domain.model.FoodPlate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Mapping interface to map:
 *
 * - FoodPlateRegisterDTO to FoodPlate
 * - FoodPlateUpdateDto to FoodPlate
 * - FoodPlateChangeState to FoodPlate
 */
@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface FoodPlateDtoMapper {
    FoodPlate toPlatoFromSave(FoodPlateRegisterDto foodPlateRegisterDTO);
    FoodPlate toPlatoFromUpdate(FoodPlateUpdateDto foodPlateUpdateDTO);
    FoodPlate toChangeState(FoodPlateChangeState foodPlateChangeState);

    @Mapping(target = "category", source = "foodPlate.category.name")
    FoodPlateDto toFoodPlateDto(FoodPlate foodPlate);


}
