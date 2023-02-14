package com.pragma.userservice.domain.spi;

import com.pragma.userservice.domain.model.Persona;

public interface IPersonaPersistencePort {
    Persona getPersona(Long id);
    Persona getPersonaByEmail(String email);
    Persona getPersonaByEmailAndPassword(String email, String password);
    void savePersona(Persona personaModel);
}
