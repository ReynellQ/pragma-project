package com.pragma.foodcourtservice.infrastructure.output.jpa.entity.pk;

import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.RestaurantEntity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import java.io.Serializable;

@Embeddable
public class RestaurantEmployeeEntityID implements Serializable {
    @Column(name="id_restaurant")
    private Long idRestaurant;
    @Column(name="id_user")
    private Long idUser;

    public Long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
}
