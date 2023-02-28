package com.pragma.foodcourtservice.infrastructure.output.jpa.adapter;

import com.pragma.foodcourtservice.domain.model.Order;
import com.pragma.foodcourtservice.domain.model.OrderFoodPlates;
import com.pragma.foodcourtservice.domain.spi.IOrderPersistencePort;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.OrderEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.OrderFoodPlatesEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.OrderEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IOrderFoodPlatesRepository;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IOrderFoodPlatesRepository orderFoodPlatesRepository;
    private final OrderEntityMapper orderEntityMapper;
    /**
     * Saves and retrieves an order in the persistence layer, with the food plates associated to the order.
     *
     * @param order           The order to save.
     * @param orderFoodPlates The food plates ordered and their quantities.
     */
    @Override
    public void saveOrder(Order order, List<OrderFoodPlates> orderFoodPlates) {
        OrderEntity entity = orderEntityMapper.toEntity(order);
        entity = orderRepository.save(entity);
        Long idOrder = entity.getId();
        orderFoodPlates.forEach(
                (orderFoodPlate) -> orderFoodPlate.setIdOrder(idOrder)
        );
        List<OrderFoodPlatesEntity> entities = orderFoodPlates.stream().map(
                (orderFoodPlate)->orderEntityMapper.toEntity(orderFoodPlate)
        ).collect(Collectors.toList());
        orderFoodPlatesRepository.saveAll(entities);
    }
}
