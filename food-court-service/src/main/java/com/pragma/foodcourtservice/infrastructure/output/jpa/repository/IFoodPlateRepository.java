package com.pragma.foodcourtservice.infrastructure.output.jpa.repository;

import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.FoodPlateEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFoodPlateRepository extends JpaRepository<FoodPlateEntity, Long> {
    @Query(value="SELECT * FROM FOOD_PLATE fp  where fp.id_restaurant = ?1 and fp.id_category IN ( ?2) " +
            " AND fp.active = true ORDER BY fp.id_category offset ?3 limit ?4",
            nativeQuery = true)
    List<FoodPlateEntity> findAllGroupByCategoryAndPaginated(Long idRestaurant,
                                                             List<Long> categories,
                                                             int offset,
                                                             int limit);

    @Query(value="SELECT * FROM FOOD_PLATE fp where fp.id_restaurant = ?1 AND fp.active = true ORDER BY fp.id_category offset ?2 limit ?3",
            nativeQuery = true)
    List<FoodPlateEntity> findAllAndPaginated(Long idRestaurant, int offset, int limit);
}
