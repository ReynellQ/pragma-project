package com.pragma.userservice.domain.api;

import com.pragma.userservice.domain.model.Persona;
import com.pragma.userservice.domain.model.Roles;

public interface IPersonaServicePort {
    Persona getPersona(Long id);

    Roles getRolPersona(Long id);
    Persona authPersona(String email, String password);
    void savePropietario(Persona personaModel);
}
