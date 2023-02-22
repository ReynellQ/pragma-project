package com.pragma.userservice.infrastructure.output.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Entity class mapped from table USERS in the database.
 */
@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "personal_id")
    private Long personalId;
    @Column(name = "first_name")
    private String name;
    private String lastname;
    private String phone;
    private String email;
    private String password;
    @Column(name = "id_role")
    private Integer idRole;
}
