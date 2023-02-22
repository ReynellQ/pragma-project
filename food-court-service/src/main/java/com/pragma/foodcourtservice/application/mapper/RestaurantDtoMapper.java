package com.pragma.foodcourtservice.application.mapper;

import com.pragma.foodcourtservice.application.dto.RestaurantClientResponse;
import com.pragma.foodcourtservice.application.dto.RestaurantCreateDto;
import com.pragma.foodcourtservice.domain.model.Restaurant;
import org.mapstruct.*;

/**
 * Mapping interface to map:
 *
 * - RestaurantDTO to Restaurant and vice-versa
 * - Restaurant to RestaurantClientResponse
 */
@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RestaurantDtoMapper {

    RestaurantCreateDto toDTO(Restaurant restaurantModel);

    Restaurant toRestaurant(RestaurantCreateDto dto);
    RestaurantClientResponse toClientResponse(Restaurant restaurant);
}
