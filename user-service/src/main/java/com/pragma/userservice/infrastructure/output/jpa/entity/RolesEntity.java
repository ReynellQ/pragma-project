package com.pragma.userservice.infrastructure.output.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PERSONAS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RolesEntity {
    @Id
    private Integer id;
    private String nombre;
    private String descripcion;
}
