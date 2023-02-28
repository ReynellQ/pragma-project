package com.pragma.foodcourtservice.infrastructure.input.rest;

import com.pragma.foodcourtservice.application.dto.foodplate.*;
import com.pragma.foodcourtservice.application.handler.FoodPlateHandler;
import com.pragma.foodcourtservice.infrastructure.configuration.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
@RestController
@RequestMapping("/food_plate/")
@RequiredArgsConstructor
public class FoodPlateRestController {
    private final FoodPlateHandler handler;

    @PostMapping("/save")
    @RolesAllowed("ROLE_OWNER")
    public ResponseEntity<Void> saveFoodPlate(@Valid @RequestBody FoodPlateRegisterDto foodPlateRegisterDTO){
        handler.saveFoodPlate(foodPlateRegisterDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PutMapping("/update")
    @RolesAllowed("ROLE_OWNER")
    public ResponseEntity<Void> updateFoodPlate(@Valid @RequestBody FoodPlateUpdateDto foodPlateUpdateDTO){
        handler.updateFoodPlate( foodPlateUpdateDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PatchMapping("/changeState")
    @RolesAllowed("ROLE_OWNER")
    public ResponseEntity<Void> changeStateFoodPlate(@Valid @RequestBody FoodPlateChangeState foodPlateChangeState){
        handler.changeStateFoodPlate( foodPlateChangeState);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/list/{restaurant}/{page}/{number}")
    @RolesAllowed("ROLE_CLIENT")
    public ResponseEntity<List<FoodPlateDto>> listFoodPlatesOfRestaurantToClient(
            @PathVariable Long restaurant,
            @PathVariable int page,
            @PathVariable int number,
            @Valid @RequestBody CategoryList categoryList){
        return ResponseEntity.ok().body(handler.listTheFoodPlatesByCategory(restaurant, categoryList, page, number));
    }
}
