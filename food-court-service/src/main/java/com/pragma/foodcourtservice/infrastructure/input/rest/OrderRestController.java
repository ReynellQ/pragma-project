package com.pragma.foodcourtservice.infrastructure.input.rest;

import com.pragma.foodcourtservice.application.dto.foodplate.FoodPlateRegisterDto;
import com.pragma.foodcourtservice.application.dto.order.OrderWithFoodPlatesDto;
import com.pragma.foodcourtservice.application.handler.IOrderHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/order/")
@RequiredArgsConstructor
public class OrderRestController {
    private final IOrderHandler handler;
    @PostMapping("/do")
    @RolesAllowed("ROLE_CLIENT")
    public ResponseEntity<Void> saveFoodPlate(@RequestBody OrderWithFoodPlatesDto orderWithFoodPlatesDto){
        handler.saveOrder(orderWithFoodPlatesDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
