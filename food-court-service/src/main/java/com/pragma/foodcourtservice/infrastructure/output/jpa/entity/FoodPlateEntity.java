package com.pragma.foodcourtservice.infrastructure.output.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "FOOD_PLATE")
public class FoodPlateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(name = "id_category")
    private Long idCategory;
    @ManyToOne
    @JoinColumn(name = "id_category", insertable=false, updatable=false)
    private CategoryEntity category;
    private String description;
    private Long price;
    @Column(name = "id_restaurant")

    private Long idRestaurant;
    @ManyToOne
    @JoinColumn(name = "id_restaurant", insertable=false, updatable=false)
    private RestaurantEntity restaurant;
    @Column(name = "url_image")
    private String urlImage;
    @Column(columnDefinition = "boolean default true")
    private Boolean active;


    public FoodPlateEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getIdRestaurant() {
        return idRestaurant;
    }

    public void setIdRestaurant(Long idRestaurant) {
        this.idRestaurant = idRestaurant;
    }

    public RestaurantEntity getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantEntity restaurant) {
        this.restaurant = restaurant;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
