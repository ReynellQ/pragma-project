package com.pragma.foodcourtservice.infrastructure.output.jpa.repository;

import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {
    @Query(value="SELECT * FROM CLIENT_ORDER cor WHERE cor.id_restaurant = ?1 AND cor.state = ?2 offset ?3 limit ?4",
            nativeQuery = true)
    List<OrderEntity> findTheOrdersFromRestaurantAndState(Integer state, Long idRestaurant,
                                                          Integer offset, Integer limit);
}
