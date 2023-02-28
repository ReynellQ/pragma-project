package com.pragma.foodcourtservice.infrastructure.output.jpa.repository;

import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.OrderFoodPlatesEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.pk.OrderFoodPlatesEntityID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderFoodPlatesRepository extends JpaRepository<OrderFoodPlatesEntity, OrderFoodPlatesEntityID> {

}
