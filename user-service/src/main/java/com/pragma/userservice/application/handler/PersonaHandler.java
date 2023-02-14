package com.pragma.userservice.application.handler;

import com.pragma.userservice.application.dto.PersonaDTO;
import com.pragma.userservice.application.dto.PersonaLogin;
import com.pragma.userservice.application.dto.PersonaRegister;
import com.pragma.userservice.application.mapper.PersonaDTOMapper;
import com.pragma.userservice.domain.api.IPersonaServicePort;
import com.pragma.userservice.domain.model.Persona;
import com.pragma.userservice.domain.model.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PersonaHandler implements IPersonaHandler{
    private final IPersonaServicePort iPersonaServicePort;
    private final PersonaDTOMapper personaDTOMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public PersonaDTO getPersona(Long id) {
        Persona p = iPersonaServicePort.getPersona(id);
        return personaDTOMapper.toDTO(p);
    }

    @Override
    public PersonaDTO authPersona(PersonaLogin personaLogin) {
        Persona p = iPersonaServicePort.authPersona(personaLogin.getEmail(), personaLogin.getPassword());
        return personaDTOMapper.toDTO(p);
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
        String contraseña = p.getClave();
        //Encriptar contraseña
        p.setClave(passwordEncoder.encode(p.getClave()));
        iPersonaServicePort.savePropietario(p);
    }
}
