package com.pragma.userservice.infrastructure.output.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity class mapped from table USERS in the database.
 */
@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    private Long id;
    @Column(name = "first_name")
    private String name;
    private String lastname;
    private String phone;
    private String email;
    @Column(name = "pass_word")
    private String password;
    @Column(name = "id_role")
    private Integer idRole;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String apellido) {
        this.lastname = apellido;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String celular) {
        this.phone = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String correo) {
        this.email = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String clave) {
        this.password = clave;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRol) {
        this.idRole = idRol;
    }
}
