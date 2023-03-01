package com.pragma.foodcourtservice.infrastructure.output.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_TICKET")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderTicketEntity {
    @Id
    @Column(name = "id_order")
    private Long idOrder;
    private String code;

    @OneToOne
    @JoinColumn(name = "id_order", insertable=false, updatable=false)
    private OrderEntity order;
}
