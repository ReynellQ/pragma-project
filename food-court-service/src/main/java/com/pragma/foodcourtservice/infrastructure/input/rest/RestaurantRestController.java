package com.pragma.foodcourtservice.infrastructure.input.rest;

import com.pragma.foodcourtservice.application.dto.RestaurantDTO;
import com.pragma.foodcourtservice.application.handler.IRestaurantHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant/")
public class RestaurantRestController {
    private final IRestaurantHandler handler;

    public RestaurantRestController(IRestaurantHandler handler) {
        this.handler = handler;
    }
    @PostMapping("/save")
    public ResponseEntity<Void> saveRestaurant(@RequestBody RestaurantDTO restaurantDTO){
        handler.saveRestaurant(restaurantDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
