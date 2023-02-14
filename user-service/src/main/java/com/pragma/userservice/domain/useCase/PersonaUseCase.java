package com.pragma.userservice.domain.useCase;

import com.pragma.userservice.domain.api.IPersonaServicePort;
import com.pragma.userservice.domain.exception.CannotLoginException;
import com.pragma.userservice.domain.model.Persona;
import com.pragma.userservice.domain.model.Roles;
import com.pragma.userservice.domain.spi.IPersonaPersistencePort;
import com.pragma.userservice.domain.spi.IRolesPersistencePort;

public class PersonaUseCase implements IPersonaServicePort {

    private final IPersonaPersistencePort personaPersistencePort;
    private final IRolesPersistencePort rolesPersistencePort;

    public PersonaUseCase(IPersonaPersistencePort personaPersistencePort, IRolesPersistencePort rolesPersistencePort){
        this.personaPersistencePort = personaPersistencePort;
        this.rolesPersistencePort = rolesPersistencePort;
    }
    @Override
    public Persona getPersona(Long id) {
        return personaPersistencePort.getPersona(id);
    }

    @Override
    public Roles getRolPersona(Long id) {
        Persona p = getPersona(id);
        return rolesPersistencePort.getRol(p.getIdRol());
    }

    @Override
    public Persona authPersona(String email, String password) {
        Persona p = personaPersistencePort.getPersonaByEmail(email);
        if(!p.getClave().equals(password))
            throw new CannotLoginException();
        return p;
    }

    @Override
    public void savePropietario(Persona personaModel) {
        personaPersistencePort.savePersona(personaModel);
    }
}
