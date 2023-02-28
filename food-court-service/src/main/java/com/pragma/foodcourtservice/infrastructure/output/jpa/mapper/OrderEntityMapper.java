package com.pragma.foodcourtservice.infrastructure.output.jpa.mapper;

import com.pragma.foodcourtservice.domain.model.Order;
import com.pragma.foodcourtservice.domain.model.OrderFoodPlates;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.OrderEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.OrderFoodPlatesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Mapping interface to map:
 *
 * - Order to OrderEntity.
 * - OrderFoodPlates to OrderFoodPlatesEntity.
 */
@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface OrderEntityMapper {
    OrderEntity toEntity(Order order);
    @Mapping(target = "id.idOrder", source = "orderFoodPlates.idOrder")
    @Mapping(target = "id.idFoodPlate", source = "orderFoodPlates.idFoodPlate")
    @Mapping(target = "quantity", source = "orderFoodPlates.quantity")
    OrderFoodPlatesEntity toEntity(OrderFoodPlates orderFoodPlates);
}
