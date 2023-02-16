package com.pragma.foodcourtservice.infrastructure.input.rest;

import com.pragma.foodcourtservice.application.dto.FoodPlateRegisterDTO;
import com.pragma.foodcourtservice.application.dto.FoodPlateUpdateDto;
import com.pragma.foodcourtservice.application.handler.FoodPlateHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/plate/")
public class FoodPlateRestController {
    private final FoodPlateHandler handler;

    public FoodPlateRestController(FoodPlateHandler handler) {
        this.handler = handler;
    }
    @PostMapping("/save")
    public ResponseEntity<Void> saveFoodPlate(@RequestBody FoodPlateRegisterDTO foodPlateRegisterDTO){
        handler.saveFoodPlate(foodPlateRegisterDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @PostMapping("/update")
    public ResponseEntity<Void> updateFoodPlate(@RequestBody FoodPlateUpdateDto foodPlateUpdateDTO){
        handler.updateFoodPlate(foodPlateUpdateDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
