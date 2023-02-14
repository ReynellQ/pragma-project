package com.pragma.userservice.application.handler;

import com.pragma.userservice.application.dto.PersonaDTO;
import com.pragma.userservice.application.dto.PersonaLogin;
import com.pragma.userservice.application.dto.PersonaRegister;

public interface IPersonaHandler {
    PersonaDTO getPersona(Long id);
    PersonaDTO authPersona(PersonaLogin personaLogin);
    void saveUsuario(PersonaDTO personaModel);
    void savePropietario(PersonaRegister personaModel);
}
