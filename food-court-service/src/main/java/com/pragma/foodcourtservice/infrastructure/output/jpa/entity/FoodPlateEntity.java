package com.pragma.foodcourtservice.infrastructure.output.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "FOOD_PLATE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
}
