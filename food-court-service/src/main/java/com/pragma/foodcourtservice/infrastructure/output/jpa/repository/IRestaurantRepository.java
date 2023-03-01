package com.pragma.foodcourtservice.infrastructure.output.jpa.repository;

import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

    @Query(value="SELECT * FROM RESTAURANT r ORDER BY r.name offset ?1 limit ?2", nativeQuery = true)
    public List<RestaurantEntity> findAllSortedByNameAndPaginated( int offset, int limit);
}
