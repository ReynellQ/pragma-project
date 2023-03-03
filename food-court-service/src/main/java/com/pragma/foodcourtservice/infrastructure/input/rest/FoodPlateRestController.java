package com.pragma.foodcourtservice.infrastructure.input.rest;

import com.pragma.foodcourtservice.application.dto.foodplate.*;
import com.pragma.foodcourtservice.application.handler.FoodPlateHandler;
import com.pragma.foodcourtservice.infrastructure.configuration.jwt.JwtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api(value = "Food plate controller")
@RestController
@RequestMapping("/food_plate/")
@RequiredArgsConstructor
@Validated
public class FoodPlateRestController {
    private final FoodPlateHandler handler;

    @ApiOperation(value = "Save a food plate.")
    @PostMapping("/save")
    @RolesAllowed("ROLE_OWNER")
    public ResponseEntity<Void> saveFoodPlate(@Valid @RequestBody FoodPlateRegisterDto foodPlateRegisterDTO){
        handler.saveFoodPlate(foodPlateRegisterDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @ApiOperation(value = "Update a food plate.")
    @PutMapping("/update")
    @RolesAllowed("ROLE_OWNER")
    public ResponseEntity<Void> updateFoodPlate(@Valid @RequestBody FoodPlateUpdateDto foodPlateUpdateDTO){
        handler.updateFoodPlate( foodPlateUpdateDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @ApiOperation(value = "Change the current state of a food plate.")
    @PatchMapping("/changeState")
    @RolesAllowed("ROLE_OWNER")
    public ResponseEntity<Void> changeStateFoodPlate(@RequestBody @Valid FoodPlateChangeState foodPlateChangeState){
        handler.changeStateFoodPlate( foodPlateChangeState);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @ApiOperation(value = "Get a list of food plates from a restaurant paginated.", response = List.class)
    @GetMapping("/list/{restaurant}/{page}/{number}")
    public ResponseEntity<List<FoodPlateDto>> listFoodPlatesOfRestaurantToClient(
            @Valid @PathVariable Long restaurant,
            @Valid @PathVariable int page,
            @Valid @PathVariable int number,
            @Valid @RequestBody CategoryList categoryList){
        return ResponseEntity.ok().body(handler.listTheFoodPlatesByCategory(restaurant, categoryList, page, number));
    }
}
