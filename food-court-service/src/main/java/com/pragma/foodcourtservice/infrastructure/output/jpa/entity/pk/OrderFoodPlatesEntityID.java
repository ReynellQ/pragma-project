package com.pragma.foodcourtservice.infrastructure.output.jpa.entity.pk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderFoodPlatesEntityID implements Serializable {
    @Column(name = "id_order")
    private Long idOrder;
    @Column(name = "id_foodplate")
    private Long idFoodPlate;
}
