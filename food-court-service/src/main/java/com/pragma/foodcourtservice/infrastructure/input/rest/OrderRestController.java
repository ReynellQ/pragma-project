package com.pragma.foodcourtservice.infrastructure.input.rest;

import com.pragma.foodcourtservice.application.dto.order.DeliverOrderDto;
import com.pragma.foodcourtservice.application.dto.order.OrderIdDto;
import com.pragma.foodcourtservice.application.dto.order.OrderResponseDto;
import com.pragma.foodcourtservice.application.dto.order.OrderWithFoodPlatesDto;
import com.pragma.foodcourtservice.application.handler.IOrderHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@Api(value = "Order controller")
@RestController
@RequestMapping("/order/")
@RequiredArgsConstructor
public class OrderRestController {
    private final IOrderHandler handler;

    @ApiOperation(value = "Save an order, only if you are a client.")
    @PostMapping("/do")
    @RolesAllowed("ROLE_CLIENT")
    public ResponseEntity<Void> saveFoodPlate(@RequestBody OrderWithFoodPlatesDto orderWithFoodPlatesDto){
        handler.saveOrder(orderWithFoodPlatesDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @ApiOperation(value = "List all the order of your restaurant, only for employees.", response = List.class)
    @GetMapping("/list/{page}/{limit}")
    @RolesAllowed("ROLE_EMPLOYEE")
    public ResponseEntity<List<OrderResponseDto>> listOrdersByStatePaginated(@Valid @PathVariable Integer page,
                                                                             @Valid @PathVariable Integer limit,
                                                                             @Valid @RequestParam Integer state){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                handler.getOrdersFilterByState(state, page, limit)
        );
    }

    @ApiOperation(value = "Assing yourself to an order, only if you're employee.")
    @PostMapping("/assign")
    @RolesAllowed("ROLE_EMPLOYEE")
    public ResponseEntity<Void> assignToAnOrder(@Valid @RequestBody OrderIdDto orderIdDto){
        handler.assignToAnOrder(orderIdDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @ApiOperation(value = "Notify to a client that their order is ready. Only for employees.")
    @PostMapping("/notify")
    @RolesAllowed("ROLE_EMPLOYEE")
    public ResponseEntity<Void> notifyIsReadyTheOrder(@Valid @RequestBody OrderIdDto orderIdDto){
        handler.notifyTheOrderIsReady(orderIdDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @ApiOperation(value = "Deliver an order to the client. Only for employees.")
    @PostMapping("/deliver")
    @RolesAllowed("ROLE_EMPLOYEE")
    public ResponseEntity<Void> deliverTheOrder(@Valid @RequestBody DeliverOrderDto deliverOrderDto){
        handler.deliverAnOrder(deliverOrderDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @ApiOperation(value = "Cancel an order, only for clients and if the order is PENDING.")
    @PostMapping("/cancel")
    @RolesAllowed("ROLE_CLIENT")
    public ResponseEntity<Void> cancelAnOrder(@Valid @RequestBody OrderIdDto orderIdDto){
        handler.cancelAnOrder(orderIdDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
