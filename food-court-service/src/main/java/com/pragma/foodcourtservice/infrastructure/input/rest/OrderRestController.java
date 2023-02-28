package com.pragma.foodcourtservice.infrastructure.input.rest;

import com.pragma.foodcourtservice.application.dto.foodplate.FoodPlateRegisterDto;
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
}
