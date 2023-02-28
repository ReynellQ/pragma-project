package com.pragma.foodcourtservice.infrastructure.output.jpa.entity;

import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.pk.OrderFoodPlatesEntityID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name ="ORDER_FOODPLATES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderFoodPlatesEntity {
    @EmbeddedId
    private OrderFoodPlatesEntityID id;
    private Long quantity;
    @ManyToOne
    @JoinColumn(name = "id_order", insertable=false, updatable=false)
    private OrderEntity order;
    @ManyToOne
    @JoinColumn(name = "id_foodplate", insertable=false, updatable=false)
    private FoodPlateEntity foodPlate;
}
