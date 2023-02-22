package com.pragma.foodcourtservice.domain.model;

public class RestaurantEmployee {
    private Long idRestaurant;
    private Long idUser;

    public RestaurantEmployee() {
    }

    public RestaurantEmployee(Long idRestaurant, Long idUser) {
        this.idRestaurant = idRestaurant;
        this.idUser = idUser;
    }

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
