package com.pragma.userservice.infrastructure.output.jpa.repository;

import com.pragma.userservice.infrastructure.output.jpa.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPersonaRepository extends JpaRepository<PersonaEntity, Long> {
    Optional<PersonaEntity> findByCorreo(String email);
    Optional<PersonaEntity> findByCorreoAndClave(String correo, String clave);

}
