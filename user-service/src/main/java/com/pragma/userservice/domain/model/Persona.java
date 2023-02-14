package com.pragma.userservice.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter //Adds the getters, nothing more
@Setter //Adds the setters, nothing more
public class PersonaModel {
    private Long id;
    private String nombre;
    private String apellido;
    private String celular;
    private String correo;
    private String clave;
    private int idRol; // No será RolesModel?
}
