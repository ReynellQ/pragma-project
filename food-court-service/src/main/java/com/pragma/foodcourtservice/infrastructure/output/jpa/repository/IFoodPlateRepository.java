package com.pragma.foodcourtservice.infrastructure.output.jpa.repository;

import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.FoodPlateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFoodPlateRepository extends JpaRepository<FoodPlateEntity, Long> {
}
