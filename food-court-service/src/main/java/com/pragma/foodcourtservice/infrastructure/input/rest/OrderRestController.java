package com.pragma.foodcourtservice.infrastructure.input.rest;

import com.pragma.foodcourtservice.application.dto.order.DeliverOrderDto;
import com.pragma.foodcourtservice.application.dto.order.OrderIdDto;
import com.pragma.foodcourtservice.application.dto.order.OrderResponseDto;
import com.pragma.foodcourtservice.application.dto.order.OrderWithFoodPlatesDto;
import com.pragma.foodcourtservice.application.handler.IOrderHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/list/{page}/{limit}")
    @RolesAllowed("ROLE_EMPLOYEE")
    public ResponseEntity<List<OrderResponseDto>> listOrdersByStatePaginated(@Valid @PathVariable Integer page,
                                                                             @Valid @PathVariable Integer limit,
                                                                             @Valid @RequestParam Integer state){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                handler.getOrdersFilterByState(state, page, limit)
        );
    }

    @PostMapping("/assign")
    @RolesAllowed("ROLE_EMPLOYEE")
    public ResponseEntity<Void> assignToAnOrder(@Valid @RequestBody OrderIdDto orderIdDto){
        handler.assignToAnOrder(orderIdDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/notify")
    @RolesAllowed("ROLE_EMPLOYEE")
    public ResponseEntity<Void> notifyIsReadyTheOrder(@Valid @RequestBody OrderIdDto orderIdDto){
        handler.notifyTheOrderIsReady(orderIdDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/deliver")
    @RolesAllowed("ROLE_EMPLOYEE")
    public ResponseEntity<Void> deliverTheOrder(@Valid @RequestBody DeliverOrderDto deliverOrderDto){
        handler.deliverAnOrder(deliverOrderDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/cancel")
    @RolesAllowed("ROLE_CLIENT")
    public ResponseEntity<Void> cancelAnOrder(@Valid @RequestBody OrderIdDto orderIdDto){
        handler.cancelAnOrder(orderIdDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
