package com.pragma.foodcourtservice.infrastructure.input.rest;

import com.pragma.foodcourtservice.application.dto.PlatosSaveDTO;
import com.pragma.foodcourtservice.application.dto.PlatosUpdateDTO;
import com.pragma.foodcourtservice.application.dto.RestauranteDTO;
import com.pragma.foodcourtservice.application.handler.PlatosHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/platos/")
public class PlatoController {
    private final PlatosHandler handler;

    public PlatoController(PlatosHandler handler) {
        this.handler = handler;
    }
    @PostMapping("/save")
    public ResponseEntity<Void> savePlato(@RequestBody PlatosSaveDTO platosSaveDTO){
        handler.savePlato(platosSaveDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
    @PostMapping("/update")
    public ResponseEntity<Void> updatePlato(@RequestBody PlatosUpdateDTO platosUpdateDTO){
        handler.updatePlato(platosUpdateDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
