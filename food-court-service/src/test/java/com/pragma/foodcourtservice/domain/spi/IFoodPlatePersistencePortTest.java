package com.pragma.foodcourtservice.domain.spi;

import com.pragma.foodcourtservice.CategoryData;
import com.pragma.foodcourtservice.FoodPlateData;
import com.pragma.foodcourtservice.RestaurantData;
import com.pragma.foodcourtservice.domain.model.FoodPlate;
import com.pragma.foodcourtservice.infrastructure.output.jpa.adapter.FoodPlateJpaAdapter;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.FoodPlateEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.FoodPlateEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IFoodPlateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IFoodPlatePersistencePortTest {
    IFoodPlateRepository foodPlateRepository;
    FoodPlateEntityMapper foodPlateEntityMapper;

    IFoodPlatePersistencePort foodPlatePersistencePort;

    @BeforeEach
    void setUp(){
        foodPlateRepository = mock(IFoodPlateRepository.class);
        foodPlateEntityMapper = mock(FoodPlateEntityMapper.class);
        foodPlatePersistencePort = new FoodPlateJpaAdapter(foodPlateRepository, foodPlateEntityMapper);
    }

    @Test
    void saveFoodPlate() {
        FoodPlate foodPlate = FoodPlateData.FOOD_PLATE_001;

        FoodPlateEntity foodPlateEntity = new FoodPlateEntity(
                1L, foodPlate.getName(), foodPlate.getIdCategory(),null, foodPlate.getDescription(),
                foodPlate.getPrice(), foodPlate.getIdRestaurant(), null, foodPlate.getUrlImage(),
                foodPlate.getActive()
        );
        when(foodPlateRepository.findById(foodPlate.getId()))
                .thenReturn(Optional.of(foodPlateEntity));
        when(foodPlateEntityMapper.toFoodPlate(foodPlateEntity))
                .thenReturn(foodPlate);
        assertEquals(foodPlate, foodPlatePersistencePort.getFoodPlate(foodPlate.getId()));
    }

    @Test
    void updateFoodPlate() {
        FoodPlate foodPlate = FoodPlateData.FOOD_PLATE_001;

        FoodPlateEntity foodPlateEntity = new FoodPlateEntity(
                foodPlate.getId(), foodPlate.getName(), foodPlate.getIdCategory(),null, foodPlate.getDescription(),
                foodPlate.getPrice(), foodPlate.getIdRestaurant(), null, foodPlate.getUrlImage(),
                foodPlate.getActive()
        );
        when(foodPlateEntityMapper.toFoodPlate(foodPlateEntity))
                .thenReturn(foodPlate);
        assertEquals(foodPlate, foodPlatePersistencePort.updateFoodPlate(foodPlate));
    }

    @Test
    void getFoodPlateCorrectly() {

    }

    @Test
    void getFoodPlateButDoesNotExist() {

    }

    @Test
    void listTheFoodPlatesByCategory() {

    }
}