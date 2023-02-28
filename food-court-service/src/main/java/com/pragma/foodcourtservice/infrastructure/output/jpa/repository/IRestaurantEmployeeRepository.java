package com.pragma.foodcourtservice.infrastructure.output.jpa.repository;

import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.RestaurantEmployeeEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.pk.RestaurantEmployeeEntityID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRestaurantEmployeeRepository extends
        JpaRepository<RestaurantEmployeeEntity, RestaurantEmployeeEntityID> {
    Optional<RestaurantEmployeeEntity> findByIdIdUser(Long idUser);
}
