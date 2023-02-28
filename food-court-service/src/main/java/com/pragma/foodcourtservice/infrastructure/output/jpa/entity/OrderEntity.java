package com.pragma.foodcourtservice.infrastructure.output.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "CLIENT_ORDER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "id_client")
    private Long idClient;
    private LocalDate date;
    private Integer state;
    @Column(name = "id_chef")
    private Long idChef;
    @Column(name = "id_restaurant")
    private Long idRestaurant;
    @JoinColumns({
            @JoinColumn(name = "id_chef", insertable=false, updatable=false),
            @JoinColumn(name = "id_restaurant", insertable=false, updatable=false)
    })
    @ManyToOne
    private RestaurantEmployeeEntity restaurantEmployee;
    @OneToMany
    private List<OrderFoodPlatesEntity> orderFoodPlates;
}
