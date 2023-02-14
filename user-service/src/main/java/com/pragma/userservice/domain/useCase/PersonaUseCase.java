package com.pragma.userservice.domain.useCase;

import com.pragma.userservice.domain.api.IPersonaChecker;
import com.pragma.userservice.domain.api.IPersonaServicePort;
import com.pragma.userservice.domain.exception.BadDataException;
import com.pragma.userservice.domain.exception.CannotLoginException;
import com.pragma.userservice.domain.model.Persona;
import com.pragma.userservice.domain.model.Roles;
import com.pragma.userservice.domain.spi.IPersonaPersistencePort;
import com.pragma.userservice.domain.spi.IRolesPersistencePort;
//No es mejor implementar un handler en la capa de infraestructura? No
public class PersonaUseCase implements IPersonaServicePort {
    private final IPersonaPersistencePort personaPersistencePort;
    private final IRolesPersistencePort rolesPersistencePort;
    private final IPersonaChecker personaChecker;

    public PersonaUseCase(IPersonaPersistencePort personaPersistencePort, IRolesPersistencePort rolesPersistencePort,
                          IPersonaChecker personaChecker){
        this.personaPersistencePort = personaPersistencePort;
        this.rolesPersistencePort = rolesPersistencePort;
        this.personaChecker = personaChecker;
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
        //Verificar correo
        if(!personaChecker.emailChecker(personaModel.getCorreo()))
            throw new BadDataException();
        //Verificar número de telefono
        if(!personaChecker.phoneChecker(personaModel.getCelular()))
            throw new BadDataException();
        personaPersistencePort.savePersona(personaModel);
    }
}
