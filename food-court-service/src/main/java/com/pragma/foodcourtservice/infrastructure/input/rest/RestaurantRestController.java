package com.pragma.foodcourtservice.infrastructure.input.rest;

import com.pragma.foodcourtservice.application.dto.restaurant.RestaurantClientResponse;
import com.pragma.foodcourtservice.application.dto.restaurant.RestaurantCreateDto;
import com.pragma.foodcourtservice.application.dto.restaurant.RestaurantEmployeeDto;
import com.pragma.foodcourtservice.application.handler.IRestaurantHandler;
import com.pragma.foodcourtservice.infrastructure.configuration.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/restaurant/")
@RequiredArgsConstructor
public class RestaurantRestController {
    private final IRestaurantHandler handler;

    @PostMapping("/save")
    @RolesAllowed("ROLE_OWNER")
    public ResponseEntity<Void> saveRestaurant(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
                                               @RequestBody RestaurantCreateDto restaurantCreateDTO){
        handler.saveRestaurant(restaurantCreateDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/list/{page}/{number}")
    @RolesAllowed("ROLE_CLIENT")
    public ResponseEntity<List<RestaurantClientResponse>> listRestaurantsToClient( @PathVariable Integer page,
                                                                                   @PathVariable Integer number){
        return ResponseEntity
                .ok(
                        handler.listAllAlphabeticallyRestaurantsPaginated(page,number)
                );
    }
    @PostMapping("/saveEmployee")
    @RolesAllowed("ROLE_OWNER")
    public ResponseEntity<Void> saveAnEmployeeOfTheRestaurant( @RequestBody RestaurantEmployeeDto restaurantEmployeeDto){
        handler.saveAnEmployeeOfARestaurant(restaurantEmployeeDto);
        return ResponseEntity.ok().build();
    }
}
