package com.pragma.foodcourtservice.application.handler;

import com.pragma.foodcourtservice.application.dto.order.OrderWithFoodPlatesDto;
import com.pragma.foodcourtservice.application.mapper.OrderDtoMapper;
import com.pragma.foodcourtservice.domain.api.IOrderServicePort;
import com.pragma.foodcourtservice.domain.model.Order;
import com.pragma.foodcourtservice.domain.model.OrderFoodPlates;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderHandler implements IOrderHandler{
    private final IOrderServicePort orderServicePort;
    private final OrderDtoMapper orderDtoMapper;
    /**
     * Saves an order with the associated food plates and their quantities in the application.
     *
     * @param orderWithFoodPlatesDto the info of the order and the food plates associated.
     */
    @Override
    public void saveOrder(OrderWithFoodPlatesDto orderWithFoodPlatesDto) {
        Order order = orderDtoMapper.toOrder(orderWithFoodPlatesDto);
        List<OrderFoodPlates> orderFoodPlates = orderWithFoodPlatesDto
                .getFoodPlateOrderDtoList()
                .stream().map(
                        (orderFoodPlate) -> orderDtoMapper.toOrderFoodPlates(orderFoodPlate)
                ).collect(Collectors.toList());
        orderServicePort.saveOrder(order, orderFoodPlates);
    }
}
