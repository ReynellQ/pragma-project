package com.pragma.userservice.application.handler;

import com.pragma.userservice.application.dto.PersonaDTO;
import com.pragma.userservice.application.dto.PersonaLogin;
import com.pragma.userservice.application.dto.PersonaRegister;
import com.pragma.userservice.application.mapper.PersonaDTOMapper;
import com.pragma.userservice.domain.api.IPersonaServicePort;
import com.pragma.userservice.domain.model.Persona;
import com.pragma.userservice.domain.model.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PersonaHandler implements IPersonaHandler{
    private final IPersonaServicePort iPersonaServicePort;
    private final PersonaDTOMapper personaDTOMapper;

    @Override
    public PersonaDTO getPersona(Long id) {
        Persona p = iPersonaServicePort.getPersona(id);
        Roles r = iPersonaServicePort.getRolPersona(id);
        return personaDTOMapper.toDTO(p, r);
    }

    @Override
    public PersonaDTO authPersona(PersonaLogin personaLogin) {
        Persona p = iPersonaServicePort.authPersona(personaLogin.getEmail(), personaLogin.getPassword());
        Roles r = iPersonaServicePort.getRolPersona(p.getId());
        return personaDTOMapper.toDTO(p, r);
    }

    @Override
    public void saveUsuario(PersonaDTO personaDto) {
        iPersonaServicePort.savePropietario(personaDTOMapper.toPersona(personaDto));
    }

    @Override
    public void savePropietario(PersonaRegister personaRegister) {
        PersonaDTO newPersona = personaDTOMapper.toRegister(personaRegister);
        Persona p = personaDTOMapper.toPersona(newPersona);
        p.setIdRol(2);
        iPersonaServicePort.savePropietario(p);
    }
}
