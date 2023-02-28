package com.pragma.foodcourtservice.domain.useCase;

import com.pragma.foodcourtservice.domain.api.IOrderServicePort;
import com.pragma.foodcourtservice.domain.api.IPersistentLoggedUser;
import com.pragma.foodcourtservice.domain.exception.InvalidOrderException;
import com.pragma.foodcourtservice.domain.exception.NoPlatesException;
import com.pragma.foodcourtservice.domain.exception.NotAClientException;
import com.pragma.foodcourtservice.domain.model.*;
import com.pragma.foodcourtservice.domain.spi.IFoodPlatePersistencePort;
import com.pragma.foodcourtservice.domain.spi.IOrderPersistencePort;
import com.pragma.foodcourtservice.domain.spi.IRestaurantPersistencePort;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;
    private final IFoodPlatePersistencePort foodPlatePersistencePort;
    private final IPersistentLoggedUser persistentLoggedUser;
    /**
     * Saves an order with the associated food plates and their quantities. The process consists on, and in this order:
     * - Verify if the order contains food plates. In other case return InvalidOrderException.
     * - Verify if all the food plates belongs to the restaurant and are active. If a food plate doesn't exist, return
     * FoodPlateNotFoundException. If a food plate doesn't belong to the restaurant of the order,
     * return InvalidOrderException.
     * - Verify if all the orders of the food plates are positive. In other case return InvalidOrderException.
     * - Sets the id_client to the id of the logged client, in case the logged user isn't a client,
     * return NotAClientException.
     * - Sets the date to the current date.
     * - Sets the state to PENDING.
     * - Saves the order.
     *
     * @param order      The order to do
     * @param foodPlates The food plates
     */
    @Override
    public void saveOrder(Order order, List<OrderFoodPlates> foodPlates) {
        Long idRestaurant = order.getIdRestaurant();
        if(foodPlates.isEmpty()){
            throw new NoPlatesException();
        }
        foodPlates.forEach( (orderFoodPlate)->{
            FoodPlate foodPlate = foodPlatePersistencePort.getFoodPlate(orderFoodPlate.getIdFoodPlate());
            if(foodPlate.getIdRestaurant() != idRestaurant || orderFoodPlate.getQuantity() < 0 ||
                    !foodPlate.getActive()){
                throw new InvalidOrderException();
            }
        });
        User client = persistentLoggedUser.getLoggedUser();
        if(client.getIdRole() != ROLES.CLIENT ){
            throw new NotAClientException();
        }
        order.setIdClient(client.getId());
        order.setDate(LocalDate.now());
        order.setState(OrderState.PENDING);
        orderPersistencePort.saveOrder(order, foodPlates);
    }
}
