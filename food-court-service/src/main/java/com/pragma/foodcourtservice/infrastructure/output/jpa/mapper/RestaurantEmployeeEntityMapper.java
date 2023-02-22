package com.pragma.foodcourtservice.infrastructure.output.jpa.mapper;

import com.pragma.foodcourtservice.domain.model.RestaurantEmployee;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.RestaurantEmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Mapping interface to map:
 *
 * -  RestaurantEmployee to RestaurantEmployeeEntity.
 */
@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RestaurantEmployeeEntityMapper {
    @Mapping(target = "restaurantEmployeeEntityID.idRestaurant", source = "restaurantEmployee.idRestaurant")
    @Mapping(target = "restaurantEmployeeEntityID.idUser", source = "restaurantEmployee.idUser")
    RestaurantEmployeeEntity toEntity(RestaurantEmployee restaurantEmployee);
}
