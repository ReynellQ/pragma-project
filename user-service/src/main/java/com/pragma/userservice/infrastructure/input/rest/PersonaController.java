package com.pragma.userservice.infrastructure.input.rest;

import com.pragma.userservice.application.dto.PersonaDTO;
import com.pragma.userservice.application.dto.PersonaRegister;
import com.pragma.userservice.application.handler.IPersonaHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persona/")
@RequiredArgsConstructor
public class PersonaController {
    private final IPersonaHandler personaHandler;

    @PostMapping("/savePropietario")
    public ResponseEntity<Void> savePropietario(@RequestBody PersonaRegister personaRegister){
        personaHandler.savePropietario(personaRegister);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> getPersona(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(personaHandler.getPersona(id));
    }
}
