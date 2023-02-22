package com.pragma.foodcourtservice.infrastructure.output.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryEntity {
    @Id
    private Long id;
    private String name;
    private String description;
}
