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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/restaurant/")
@RequiredArgsConstructor
@Validated
public class RestaurantRestController {
    private final IRestaurantHandler handler;

    @PostMapping("/save")
    @RolesAllowed("ROLE_OWNER")
    public ResponseEntity<Void> saveRestaurant(@Valid @RequestBody RestaurantCreateDto restaurantCreateDTO){
        handler.saveRestaurant(restaurantCreateDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/list/{page}/{number}")
    @RolesAllowed("ROLE_CLIENT")
    public ResponseEntity<List<RestaurantClientResponse>> listRestaurantsToClient(@Valid @PathVariable Integer page,
                                                                                  @Valid @PathVariable Integer number){
        return ResponseEntity
                .ok(
                        handler.listAllAlphabeticallyRestaurantsPaginated(page,number)
                );
    }
    @PostMapping("/saveEmployee")
    @RolesAllowed("ROLE_OWNER")
    public ResponseEntity<Void> saveAnEmployeeOfTheRestaurant(
            @Valid @RequestBody RestaurantEmployeeDto restaurantEmployeeDto){
        handler.saveAnEmployeeOfARestaurant(restaurantEmployeeDto);
        return ResponseEntity.ok().build();
    }
}
