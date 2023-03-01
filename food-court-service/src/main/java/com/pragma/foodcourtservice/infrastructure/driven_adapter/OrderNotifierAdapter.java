package com.pragma.foodcourtservice.infrastructure.driven_adapter;

import com.pragma.foodcourtservice.domain.api.IOrderNotifierPort;
import com.pragma.foodcourtservice.domain.model.*;
import com.pragma.foodcourtservice.domain.spi.IMessagingMicroServiceClientPort;
import com.pragma.foodcourtservice.domain.spi.IOrderPersistencePort;
import com.pragma.foodcourtservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourtservice.domain.spi.IUserMicroServiceClientPort;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class OrderNotifierAdapter implements IOrderNotifierPort {
    private final IMessagingMicroServiceClientPort messagingMicroServiceClientPort;
    private final IUserMicroServiceClientPort userMicroServiceClientPort;
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IOrderPersistencePort orderPersistencePort;
    @Override
    public void sendMessage(Order order) {
        Long idOrder = order.getId();
        Long idClient = order.getIdClient();
        User client = userMicroServiceClientPort.getUserById(idClient);
        Restaurant restaurant = restaurantPersistencePort.getRestaurant(order.getIdRestaurant());
        String pin = generateRandomCode();
        ReadyOrderMessage readyOrderMessage = new ReadyOrderMessage(idOrder, restaurant.getName(), client.getPhone(),
                pin);
        orderPersistencePort.saveOrderTicket( new OrderTicket(order.getId(), pin));
        messagingMicroServiceClientPort.sendMessage(readyOrderMessage);
    }

    @Override
    public String generateRandomCode() {
        char[] code = new char[]{'0', '0', '0', '0'};
        Random random = new Random();
        for(int i = 0 ; i < 4 ; ++i){
            code[i]+= random.nextInt(10);
        }
        return new String(code);
    }
}
