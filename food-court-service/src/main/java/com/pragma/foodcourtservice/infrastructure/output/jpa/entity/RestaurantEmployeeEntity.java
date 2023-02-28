package com.pragma.foodcourtservice.infrastructure.output.jpa.entity;

import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.pk.RestaurantEmployeeEntityID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name ="RESTAURANT_EMPLOYEE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RestaurantEmployeeEntity {
    @EmbeddedId
    private RestaurantEmployeeEntityID id;
    @ManyToOne
    @JoinColumn(name = "id_restaurant", insertable=false, updatable=false)
    private RestaurantEntity restaurant;
}
