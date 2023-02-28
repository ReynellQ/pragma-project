package com.pragma.foodcourtservice.application.mapper;

import com.pragma.foodcourtservice.application.dto.order.FoodPlateOrderDto;
import com.pragma.foodcourtservice.application.dto.order.OrderWithFoodPlatesDto;
import com.pragma.foodcourtservice.domain.model.Order;
import com.pragma.foodcourtservice.domain.model.OrderFoodPlates;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Mapping interface to map:
 *
 * - OrderWithFoodPlatesDto to Order
 * - FoodPlateOrderDto to OrderFoodPlates
 * - FoodPlateChangeState to FoodPlate
 */
@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface OrderDtoMapper {
    Order toOrder(OrderWithFoodPlatesDto orderWithFoodPlatesDto);
    OrderFoodPlates toOrderFoodPlates(FoodPlateOrderDto foodPlateOrderDto);
}
