package com.pragma.foodcourtservice.application.mapper;

import com.pragma.foodcourtservice.application.dto.RestaurantDTO;
import com.pragma.foodcourtservice.domain.model.Restaurant;
import org.mapstruct.*;

/**
 * Mapping interface to map:
 *
 * - RestaurantDTO to Restaurant and vice-versa
 */
@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RestaurantDTOMapper {

    RestaurantDTO toDTO(Restaurant restaurantModel);
    @InheritInverseConfiguration(name = "toDTO")
    Restaurant toRestaurant(RestaurantDTO dto);
}
