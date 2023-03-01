package com.pragma.foodcourtservice.infrastructure.output.jpa.repository;

import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.OrderTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderTicketRepository extends JpaRepository<OrderTicketEntity, Long> {
}
