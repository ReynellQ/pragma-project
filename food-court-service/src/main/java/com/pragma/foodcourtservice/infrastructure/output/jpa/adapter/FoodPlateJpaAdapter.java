package com.pragma.foodcourtservice.infrastructure.output.jpa.adapter;

import com.pragma.foodcourtservice.domain.model.FoodPlate;
import com.pragma.foodcourtservice.domain.spi.IFoodPlatePersistencePort;
import com.pragma.foodcourtservice.infrastructure.exception.FoodPlateNotFoundException;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.FoodPlateEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.FoodPlateEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IFoodPlateRepository;

import java.util.Optional;
/**
 * Class adapter that implements the methods to communicate with the Food plate entity on persistence layer and the Food
 * plate model in the domain layer. It uses the JpaRepository and a mapper to map FoodPlateEntity to FoodPlate.
 */
public class FoodPlateJpaAdapter implements IFoodPlatePersistencePort {
    private final IFoodPlateRepository platoRepository;
    private final FoodPlateEntityMapper foodPlateEntityMapper;

    public FoodPlateJpaAdapter(IFoodPlateRepository platoRepository, FoodPlateEntityMapper foodPlateEntityMapper) {
        this.platoRepository = platoRepository;
        this.foodPlateEntityMapper = foodPlateEntityMapper;
    }

    /**
     * Saves a food plate in the persistence layer.
     * @param foodPlate the food plate to be saved.
     */
    @Override
    public void saveFoodPlate(FoodPlate foodPlate) {
        platoRepository.save(foodPlateEntityMapper.toEntity(foodPlate));
    }

    /**
     * Updates a food plate in the persistence layer.
     * @param foodPlate the food plate to be updated.
     * @return the food plate updated.
     */
    @Override
    public FoodPlate updateFoodPlate(FoodPlate foodPlate) {
        platoRepository.save(foodPlateEntityMapper.toEntity(foodPlate));
        return foodPlate;
    }

    /**
     * Gets a food plate from the persistence layer given its id.
     * @param id the food plate's id to be searched.
     * @return The food plate obtained.
     * @throws FoodPlateNotFoundException if isn't a food plate with the id specified.
     */
    @Override
    public FoodPlate getFoodPlate(Long id) {
        Optional<FoodPlateEntity> entityOptional = platoRepository.findById(id);
        if(entityOptional.isEmpty())
            throw new FoodPlateNotFoundException();
        return foodPlateEntityMapper.toFoodPlate(entityOptional.get());
    }
}
