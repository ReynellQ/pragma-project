package com.pragma.foodcourtservice.domain.useCase;

import com.pragma.foodcourtservice.domain.api.IFoodPlateServicePort;
import com.pragma.foodcourtservice.domain.api.IFoodPlateValidator;
import com.pragma.foodcourtservice.domain.api.IPersistentLoggedUser;
import com.pragma.foodcourtservice.domain.exception.IncorrectDataException;
import com.pragma.foodcourtservice.domain.exception.ForbiddenUpdateException;
import com.pragma.foodcourtservice.domain.exception.NotAllowedRestaurantException;
import com.pragma.foodcourtservice.domain.exception.NotAnOwnerException;
import com.pragma.foodcourtservice.domain.model.FoodPlate;
import com.pragma.foodcourtservice.domain.model.ROLES;
import com.pragma.foodcourtservice.domain.model.Restaurant;
import com.pragma.foodcourtservice.domain.model.User;
import com.pragma.foodcourtservice.domain.spi.ICategoryPersistencePort;
import com.pragma.foodcourtservice.domain.spi.IFoodPlatePersistencePort;
import com.pragma.foodcourtservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourtservice.domain.spi.IUserMicroServiceClientPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Class FoodPlateUseCase that implements the interface IFoodPlateServicePort, and defines the business logic that be
 * used by the API.
 */
@RequiredArgsConstructor
public class FoodPlateUseCase implements IFoodPlateServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final IFoodPlatePersistencePort foodPlatePersistencePort;
    private final IFoodPlateValidator foodPlateValidator;
    private final IPersistentLoggedUser persistentLoggedUser;

    /**
     * Saves a food plate in the application. Validates if the id of the restaurant belongs to an existing restaurant
     * and if the price of the food plate is valid, if the user exists and is an owner, and if the user is the owner
     * of the restaurant provided.
     * @param foodPlate the user to be saved.
     * @throws IncorrectDataException if one of the validations doesn't pass.
     */
    @Override
    public void saveFoodPlate(FoodPlate foodPlate) {
        verifyingOwnersAuthenticity(foodPlate.getIdRestaurant());
        categoryPersistencePort.getCategory(
                foodPlate.getIdCategory()   //Search the category and throws an exception if it doesn't exist
        );
        if(!foodPlateValidator.validatesPrice(foodPlate.getPrice()))
            throw new IncorrectDataException();
        foodPlatePersistencePort.saveFoodPlate(foodPlate);
    }
    /**
     * Updates a food plate in the application. Validates if the only submitted data is the description and the price,
     * and only update this if the data is not null.
     * @param foodPlate the user to be updated.
     * @throws ForbiddenUpdateException if it's submitted different data as the specified.
     * @throws IncorrectDataException if the submitted data is invalid.
     */
    @Override
    public FoodPlate updateFoodPlate(FoodPlate foodPlate) {
        FoodPlate foodPlateToUpdate = foodPlatePersistencePort.getFoodPlate(foodPlate.getId());
        verifyingOwnersAuthenticity(foodPlateToUpdate.getIdRestaurant());
        //No puede cambiar el estado de activo
        if(!foodPlateToUpdate.getActive().equals(foodPlate.getActive()) && foodPlate.getActive()!=null)
            throw new ForbiddenUpdateException();
        //No puede cambiar la categoria
        if(!foodPlateToUpdate.getIdCategory().equals(foodPlate.getIdCategory()) && foodPlate.getIdCategory() !=null)
            throw new ForbiddenUpdateException();
        //No puede cambiar el restaurante
        if(!foodPlateToUpdate.getIdRestaurant().equals(foodPlate.getIdRestaurant()) && foodPlate.getIdRestaurant()!=null)
            throw new ForbiddenUpdateException();
        //Solo actualizamos la descripcion si no es nulo
        if(foodPlate.getDescription() != null)
            foodPlateToUpdate.setDescription(foodPlate.getDescription());
        //Solo actualizamos el precio si no es nulo
        if(foodPlate.getPrice() != null)
            foodPlateToUpdate.setPrice(foodPlate.getPrice());
        if(!foodPlateValidator.validatesPrice(foodPlateToUpdate.getPrice()))
            throw new IncorrectDataException();
        return foodPlatePersistencePort.updateFoodPlate(foodPlateToUpdate);
    }

    /**
     * Changed the state of a food plate, to the state of the food plate submitted.
     *
     * @param foodPlate the food plate
     */
    @Override
    public void changeStateFoodPlate(FoodPlate foodPlate) {
        FoodPlate foodPlateToUpdate = foodPlatePersistencePort.getFoodPlate(foodPlate.getId());
        verifyingOwnersAuthenticity(foodPlateToUpdate.getIdRestaurant());
        foodPlateToUpdate.setActive(foodPlate.getActive());
        foodPlatePersistencePort.updateFoodPlate(foodPlateToUpdate);
    }

    /**
     * List all the food plates of a restaurant that have one of the submitted categories. If the list of categories
     * is empty, provide from all categories.
     * @param idRestaurant the id of the restaurant.
     * @param categories the list of the category's ids.
     * @param page the number of the page.
     * @param number the number of food plates displayed in the current page.
     *
     * @return a list with the food plates.
     */
    @Override
    public List<FoodPlate> listTheFoodPlatesByCategory(Long idRestaurant, List<Long> categories, int page, int number) {
        return foodPlatePersistencePort.listTheFoodPlatesByCategory(idRestaurant, categories, page, number);
    }

    private void verifyingOwnersAuthenticity(Long idRestaurant){
        User user = persistentLoggedUser.getLoggedUser();
        //Verifies if is an owner
        if(user.getIdRole() != ROLES.OWNER.id){
            throw new NotAnOwnerException();
        }
        Restaurant r = restaurantPersistencePort.getRestaurant(
                idRestaurant   //Search the restaurant and throws an exception if it doesn't exist
        );
        if(r.getIdOwner() != user.getId()){ //The current user isn't the owner of this restaurant
            throw new NotAllowedRestaurantException();
        }
    }
}
