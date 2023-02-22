package com.pragma.foodcourtservice.infrastructure.input.rest;

import com.pragma.foodcourtservice.application.dto.foodplate.FoodPlateChangeState;
import com.pragma.foodcourtservice.application.dto.foodplate.FoodPlateDto;
import com.pragma.foodcourtservice.application.dto.foodplate.FoodPlateRegisterDto;
import com.pragma.foodcourtservice.application.dto.foodplate.FoodPlateUpdateDto;
import com.pragma.foodcourtservice.application.handler.FoodPlateHandler;
import com.pragma.foodcourtservice.infrastructure.configuration.jwt.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food_plate/")
public class FoodPlateRestController {
    private final FoodPlateHandler handler;
    private final JwtService jwtService;

    public FoodPlateRestController(FoodPlateHandler handler, JwtService jwtService) {
        this.handler = handler;
        this.jwtService = jwtService;
    }
    @PostMapping("/save")
    public ResponseEntity<Void> saveFoodPlate(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
                                              @RequestBody FoodPlateRegisterDto foodPlateRegisterDTO){
        jwt = jwt.substring(7);
        if(!verifyRole(jwt, "OWNER")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        String email = jwtService.extractUsername(jwt);
        handler.saveFoodPlate(email, foodPlateRegisterDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @PostMapping("/update")
    public ResponseEntity<Void> updateFoodPlate(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
                                                @RequestBody FoodPlateUpdateDto foodPlateUpdateDTO){
        jwt = jwt.substring(7);
        if(!verifyRole(jwt, "OWNER")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        String email = jwtService.extractUsername(jwt);
        handler.updateFoodPlate(email, foodPlateUpdateDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * TODO Protect this endpoint
     * @param foodPlateChangeState
     * @return
     */
    @PostMapping("/changeState")
    public ResponseEntity<Void> changeStateFoodPlate(@RequestBody FoodPlateChangeState foodPlateChangeState){
        handler.changeStateFoodPlate(foodPlateChangeState);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/list/{restaurant}")
    public ResponseEntity<List<FoodPlateDto>> listFoodPlatesOfRestaurantToClient(@PathVariable Long restaurant){
        return ResponseEntity.ok().body(List.of());
    }

    private boolean verifyRole(String jwt, String expectedRole){
        return jwtService.extractRole(jwt).equals(expectedRole);
    }
}
