package com.pragma.foodcourtservice.infrastructure.output.jpa.adapter;

import com.pragma.foodcourtservice.domain.model.*;
import com.pragma.foodcourtservice.domain.spi.IOrderPersistencePort;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.OrderEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.OrderFoodPlatesEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.OrderTicketEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.OrderEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.OrderTicketEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IOrderFoodPlatesRepository;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IOrderRepository;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IOrderTicketRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class OrderJpaAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final IOrderTicketRepository orderTicketRepository;
    private final IOrderFoodPlatesRepository orderFoodPlatesRepository;
    private final OrderEntityMapper orderEntityMapper;
    private final OrderTicketEntityMapper orderTicketEntityMapper;
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

    /**
     * Gets the orders of the provided restaurant filtered by state from the persistence layer.
     *
     * @param state        the state to filter.
     * @param idRestaurant the id of the restaurant to search.
     * @param page the page to return.
     * @param limit the amount of registers per page.
     * @return a list with the orders from the provided restaurant and state.
     */
    @Override
    public List<OrderWithFoodPlates> getOrdersFilterByState(Integer state, Long idRestaurant, Integer page, Integer limit) {
        Integer offset = page*limit;
        List<OrderEntity> ordersEntity = orderRepository
                .findTheOrdersFromRestaurantAndState(state, idRestaurant, offset, limit);
        return ordersEntity
                .stream().map(
                        (orderEntity -> orderEntityMapper.toOrderWithFoodPlates(orderEntity))
                ).collect(Collectors.toList());
    }

    /**
     * Get an order in the persistence layer with the id provided.
     *
     * @param idOrder the id of the order.
     * @return the order with the id provided.
     */
    @Override
    public Order getOrder(Long idOrder) {
        Optional<OrderEntity> entity = orderRepository.findById(idOrder);
        if(entity.isEmpty()){
            throw new OrderNotFoundException();
        }
        return orderEntityMapper.toOrder(entity.get());
    }

    /**
     * Update a giving order that exists in the persistence layer. It assumes that the order previously exists.
     *
     * @param order the order to update.
     */
    @Override
    public void updateOrder(Order order) {
        //Only to know if the order previously exists.
        OrderEntity entity = orderRepository.findById(order.getId()).get();
        entity.setDate(order.getDate());
        entity.setIdChef(order.getIdChef());
        entity.setIdClient(order.getIdClient());
        entity.setState(order.getState());
        entity.setIdRestaurant(order.getIdRestaurant());
        entity.setDate(order.getDate());
        orderRepository.save(entity);
    }

    /**
     * Saves the ticket with the order in the persistence layer. It assumes that the order previously exists.
     *
     * @param orderTicket the order ticket, with the code.
     */
    @Override
    public void saveOrderTicket(OrderTicket orderTicket) {
        OrderTicketEntity orderTicketEntity = orderTicketEntityMapper.toOrderTicketEntity(orderTicket);
        orderTicketRepository.save(orderTicketEntity);
    }

    @Override
    public OrderTicket getOrderTicketWithIdOrder(Long idOrder) {
        Optional<OrderTicketEntity> orderTicketEntity = orderTicketRepository.findById(idOrder);
        if(orderTicketEntity.isEmpty()){
            throw new OrderTicketNotFoundException();
        }
        return orderTicketEntityMapper.toOrderTicket(orderTicketEntity.get());
    }

    @Override
    public void deleteOrderTicket(Long idOrder) {
        Optional<OrderTicketEntity> orderTicketEntity = orderTicketRepository.findById(idOrder);
        if(orderTicketEntity.isEmpty()){
            throw new OrderTicketNotFoundException();
        }
        orderTicketRepository.delete(orderTicketEntity.get());
    }

    @Override
    public boolean hasActiveOrdersInTheRestaurant(Long idClient) {
        List<OrderEntity> orders = orderRepository.findByIdClient(idClient);
        for(OrderEntity order : orders){
            if(order.getState() != OrderState.DELIVERED && order.getState() !=OrderState.CANCELLLED ){
                return true;
            }
        }
        return false;
    }
}
