package com.pragma.foodcourtservice.infrastructure.output.jpa.entity;

import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.pk.RestaurantEmployeeEntityID;

import javax.persistence.*;

@Entity
@Table(name ="RESTAURANT_EMPLOYEE")
public class RestaurantEmployeeEntity {
    @EmbeddedId
    private RestaurantEmployeeEntityID restaurantEmployeeEntityID;

    @ManyToOne
    @JoinColumn(name = "id_restaurant", insertable=false, updatable=false)
    private RestaurantEntity restaurant;

    public RestaurantEmployeeEntityID getRestaurantEmployeeEntityID() {
        return restaurantEmployeeEntityID;
    }

    public void setRestaurantEmployeeEntityID(RestaurantEmployeeEntityID restaurantEmployeeEntityID) {
        this.restaurantEmployeeEntityID = restaurantEmployeeEntityID;
    }

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }
}
