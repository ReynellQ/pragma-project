package com.pragma.userservice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class RolesDTO {
    private String nombre;
    private String descripcion;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
