package com.pragma.foodcourtservice.application.handler;

import com.pragma.foodcourtservice.application.dto.order.DeliverOrderDto;
import com.pragma.foodcourtservice.application.dto.order.OrderIdDto;
import com.pragma.foodcourtservice.application.dto.order.OrderResponseDto;
import com.pragma.foodcourtservice.application.dto.order.OrderWithFoodPlatesDto;
import com.pragma.foodcourtservice.application.mapper.OrderDtoMapper;
import com.pragma.foodcourtservice.domain.api.IOrderServicePort;
import com.pragma.foodcourtservice.domain.model.Order;
import com.pragma.foodcourtservice.domain.model.OrderFoodPlates;
import com.pragma.foodcourtservice.domain.model.OrderWithFoodPlates;
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

    /**
     * Gets the orders of the restaurant filtered by state in the application.
     *
     * @param state the state provided to filter.
     * @param page  the page to return.
     * @param limit the amount of registers per page.
     * @return a list with the Orders.
     */
    @Override
    public List<OrderResponseDto> getOrdersFilterByState(Integer state, Integer page, Integer limit) {
        List<OrderWithFoodPlates> orders = orderServicePort.getOrdersFilterByState(state, page, limit);
        return orders.stream().map(
                (order)-> orderDtoMapper.toOrderResponseDto(order)
        ).collect(Collectors.toList());
    }

    /**
     * Assign the logged employee to an order to change their state.
     *
     * @param orderIdDto a DTO with the id of the order.
     */
    @Override
    public void assignToAnOrder(OrderIdDto orderIdDto) {
        orderServicePort.assignToAnOrder(orderIdDto.getIdOrder());
    }

    @Override
    public void notifyTheOrderIsReady(OrderIdDto orderIdDto) {
        orderServicePort.notifyTheOrderIsReady(orderIdDto.getIdOrder());
    }

    @Override
    public void deliverAnOrder(DeliverOrderDto deliverOrderDto) {
        orderServicePort.deliverAnOrder(deliverOrderDto.getIdOrder(), deliverOrderDto.getPin());
    }
}
