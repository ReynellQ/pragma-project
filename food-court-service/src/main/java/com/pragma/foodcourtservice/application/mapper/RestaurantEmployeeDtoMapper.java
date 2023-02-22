package com.pragma.foodcourtservice.application.mapper;

import com.pragma.foodcourtservice.application.dto.restaurant.RestaurantEmployeeDto;
import com.pragma.foodcourtservice.domain.model.RestaurantEmployee;
import com.pragma.foodcourtservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapping interface to map:
 *
 * - RestaurantEmployeeDto to User.
 * - RestaurantEmployeeDto to RestaurantEmployee.
 */
@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface RestaurantEmployeeDtoMapper {
    User toUser(RestaurantEmployeeDto restaurantEmployeeDto);
}
