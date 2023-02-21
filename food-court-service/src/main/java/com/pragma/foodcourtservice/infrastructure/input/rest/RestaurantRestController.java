package com.pragma.foodcourtservice.infrastructure.input.rest;

import com.pragma.foodcourtservice.application.dto.RestaurantClientResponse;
import com.pragma.foodcourtservice.application.dto.RestaurantCreateDto;
import com.pragma.foodcourtservice.application.handler.IRestaurantHandler;
import com.pragma.foodcourtservice.infrastructure.configuration.jwt.JwtService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant/")
public class RestaurantRestController {
    private final IRestaurantHandler handler;
    private final JwtService jwtService;

    public RestaurantRestController(IRestaurantHandler handler, JwtService jwtService) {
        this.handler = handler;
        this.jwtService = jwtService;
    }
    @PostMapping("/save")
    public ResponseEntity<Void> saveRestaurant(@RequestHeader(HttpHeaders.AUTHORIZATION) String jwt,
                                               @RequestBody RestaurantCreateDto restaurantCreateDTO){
        jwt = jwt.substring(7);
        if(!jwtService.extractRole(jwt).equals("OWNER")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        String email = jwtService.extractUsername(jwt);
        handler.saveRestaurant(email, restaurantCreateDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/list/{page}/{number}")
    public ResponseEntity<List<RestaurantClientResponse>> listRestaurantsToClient(@PathVariable Integer page,
                                                                                  @PathVariable Integer number){
        return ResponseEntity
                .ok(
                        handler.listAllAlphabeticallyRestaurantsPaginated(page,number)
                );
    }
}
