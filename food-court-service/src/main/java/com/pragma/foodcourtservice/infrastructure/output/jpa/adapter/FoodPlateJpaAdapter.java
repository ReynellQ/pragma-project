package com.pragma.foodcourtservice.infrastructure.output.jpa.adapter;

import com.pragma.foodcourtservice.domain.model.FoodPlate;
import com.pragma.foodcourtservice.domain.spi.IFoodPlatePersistencePort;
import com.pragma.foodcourtservice.infrastructure.exception.FoodPlateNotFoundException;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.FoodPlateEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.FoodPlateEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IFoodPlateRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class adapter that implements the methods to communicate with the Food plate entity on persistence layer and the Food
 * plate model in the domain layer. It uses the JpaRepository and a mapper to map FoodPlateEntity to FoodPlate.
 */
@RequiredArgsConstructor
public class FoodPlateJpaAdapter implements IFoodPlatePersistencePort {
    private final IFoodPlateRepository foodPlateRepository;
    private final FoodPlateEntityMapper foodPlateEntityMapper;


    /**
     * Saves a food plate in the persistence layer.
     * @param foodPlate the food plate to be saved.
     */
    @Override
    public void saveFoodPlate(FoodPlate foodPlate) {
        foodPlateRepository.save(foodPlateEntityMapper.toEntity(foodPlate));
    }

    /**
     * Updates a food plate in the persistence layer.
     * @param foodPlate the food plate to be updated.
     * @return the food plate updated.
     */
    @Override
    public FoodPlate updateFoodPlate(FoodPlate foodPlate) {
        foodPlateRepository.save(foodPlateEntityMapper.toEntity(foodPlate));
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
        Optional<FoodPlateEntity> entityOptional = foodPlateRepository.findById(id);
        if(entityOptional.isEmpty())
            throw new FoodPlateNotFoundException();
        return foodPlateEntityMapper.toFoodPlate(entityOptional.get());
    }

    /**
     * Gets a list from the persistence layer of all the food plates of a restaurant that have one of the submitted
     * categories. If the list of categories is empty, provide from all categories.
     * @param idRestaurant the id of the restaurant.
     * @param categories the list of the category's ids.
     * @param page the number of the page.
     * @param number the number of food plates displayed in the current page.
     *
     * @return a list with the food plates.
     */
    @Override
    public List<FoodPlate> listTheFoodPlatesByCategory(Long idRestaurant, List<Long> categories, int page, int number) {
        List<FoodPlateEntity> foodPlateEntityList;
        if(categories.isEmpty()){
            foodPlateEntityList = foodPlateRepository
                    .findAllAndPaginated(idRestaurant, page*number, number);
        }else{
            foodPlateEntityList = foodPlateRepository
                    .findAllGroupByCategoryAndPaginated(idRestaurant, categories, page*number, number);
        }
        return foodPlateEntityList.stream()
                .map(
                        (foodPlateEntity -> foodPlateEntityMapper.toFoodPlate(foodPlateEntity))
                ).collect(Collectors.toList());
    }
}
