package com.pragma.foodcourtservice.infrastructure.input.rest;

import com.pragma.foodcourtservice.application.dto.RestauranteDTO;
import com.pragma.foodcourtservice.application.handler.RestauranteHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurante/")
public class RestauranteController {
    private final RestauranteHandler handler;

    public RestauranteController(RestauranteHandler handler) {
        this.handler = handler;
    }
    @PostMapping("/save")
    public ResponseEntity<Void> saveRestaurante(@RequestBody RestauranteDTO restauranteDTO){
        handler.saveRestaurantes(restauranteDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
