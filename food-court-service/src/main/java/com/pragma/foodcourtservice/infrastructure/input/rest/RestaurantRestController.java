package com.pragma.foodcourtservice.infrastructure.input.rest;

import com.pragma.foodcourtservice.application.dto.restaurant.RestaurantClientResponse;
import com.pragma.foodcourtservice.application.dto.restaurant.RestaurantCreateDto;
import com.pragma.foodcourtservice.application.dto.restaurant.RestaurantEmployeeDto;
import com.pragma.foodcourtservice.application.handler.IRestaurantHandler;
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
import java.util.List;


@RestController
@Api(value = "Restaurant controller")
@RequestMapping("/restaurant/")
@RequiredArgsConstructor
@Validated
public class RestaurantRestController {
    private final IRestaurantHandler handler;

    @ApiOperation(value = "Save one of your restaurants. Only for OWNERS.")
    @PostMapping("/save")
    @RolesAllowed("ROLE_OWNER")
    public ResponseEntity<Void> saveRestaurant(@Valid @RequestBody RestaurantCreateDto restaurantCreateDTO){
        handler.saveRestaurant(restaurantCreateDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @ApiOperation(value = "List all restaurants of your food court. Only for clients.")
    @GetMapping("/list/{page}/{number}")
    public ResponseEntity<List<RestaurantClientResponse>> listRestaurantsToClient(@Valid @PathVariable Integer page,
                                                                                  @Valid @PathVariable Integer number){
        return ResponseEntity
                .ok(
                        handler.listAllAlphabeticallyRestaurantsPaginated(page,number)
                );
    }
    @ApiOperation(value = "Save an employee from one of your restaurants. Only for OWNERS.")
    @PostMapping("/saveEmployee")
    @RolesAllowed("ROLE_OWNER")
    public ResponseEntity<Void> saveAnEmployeeOfTheRestaurant(
            @Valid @RequestBody RestaurantEmployeeDto restaurantEmployeeDto){
        handler.saveAnEmployeeOfARestaurant(restaurantEmployeeDto);
        return ResponseEntity.ok().build();
    }
}
