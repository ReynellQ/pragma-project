package com.pragma.foodcourtservice.infrastructure.output.jpa.mapper;

import com.pragma.foodcourtservice.domain.model.OrderTicket;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.OrderTicketEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Mapping interface to map:
 *
 * - OrderTicketEntity to OrderTicket and vice-versa.
 */
@Mapper(
        componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface OrderTicketEntityMapper {
    OrderTicketEntity toOrderTicketEntity(OrderTicket orderTicket);
    OrderTicket toOrderTicket(OrderTicketEntity orderTicketEntity);
}
