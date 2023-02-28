package com.pragma.foodcourtservice.infrastructure.output.jpa.mapper;

import com.pragma.foodcourtservice.domain.model.RestaurantEmployee;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.RestaurantEmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Mapping interface to map:
 *
 * - RestaurantEmployee to RestaurantEmployeeEntity.
 * - RestaurantEmployeeEntity to RestaurantEmployee
 */
@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RestaurantEmployeeEntityMapper {
    @Mapping(target = "id.idRestaurant", source = "restaurantEmployee.idRestaurant")
    @Mapping(target = "id.idUser", source = "restaurantEmployee.idUser")
    RestaurantEmployeeEntity toEntity(RestaurantEmployee restaurantEmployee);

    @Mapping(target = "idRestaurant", source = "entity.id.idRestaurant")
    @Mapping(target = "idUser", source = "entity.id.idUser")
    RestaurantEmployee toRestaurantEmployee(RestaurantEmployeeEntity entity);
}
