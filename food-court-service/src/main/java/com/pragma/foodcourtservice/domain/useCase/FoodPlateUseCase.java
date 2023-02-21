package com.pragma.foodcourtservice.domain.useCase;

import com.pragma.foodcourtservice.domain.api.IFoodPlateServicePort;
import com.pragma.foodcourtservice.domain.api.IFoodPlateValidator;
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

/**
 * Class FoodPlateUseCase that implements the interface IFoodPlateServicePort, and defines the business logic that be
 * used by the API.
 */
public class FoodPlateUseCase implements IFoodPlateServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final ICategoryPersistencePort categoryPersistencePort;
    private final IFoodPlatePersistencePort platoPersistencePort;
    private final IFoodPlateValidator platoValidator;
    private final IUserMicroServiceClientPort userClientPort;

    public FoodPlateUseCase(IRestaurantPersistencePort restaurantPersistencePort,
                            ICategoryPersistencePort categoryPersistencePort,
                            IFoodPlatePersistencePort platoPersistencePort, IFoodPlateValidator platoValidator,
                            IUserMicroServiceClientPort userClientPort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.categoryPersistencePort = categoryPersistencePort;
        this.platoPersistencePort = platoPersistencePort;
        this.platoValidator = platoValidator;
        this.userClientPort = userClientPort;
    }
    /**
     * Saves a food plate in the application. Validates if the id of the restaurant belongs to an existing restaurant
     * and if the price of the food plate is valid, if the user exists and is an owner, and if the user is the owner
     * of the restaurant provided.
     * @param foodPlate the user to be saved.
     * @throws IncorrectDataException if one of the validations doesn't pass.
     */
    @Override
    public void saveFoodPlate(String email, FoodPlate foodPlate) {
        //Add business logic
        User user = userClientPort.getUserByEmail(email);
        //Verifies if is an owner
        if(user.getIdRole() != ROLES.OWNER.id){
            throw new NotAnOwnerException();
        }

        Restaurant r = restaurantPersistencePort.getRestaurant(
                foodPlate.getIdRestaurant()   //Search the restaurant and throws an exception if it doesn't exist
        );
        if(r.getIdOwner() != user.getId()){ //The current user isn't the owner of this restaurant
            throw new NotAllowedRestaurantException();
        }
        categoryPersistencePort.getCategory(
                foodPlate.getIdCategory()   //Search the category and throws an exception if it doesn't exist
        );
        if(!platoValidator.validatesPrice(foodPlate.getPrice()))
            throw new IncorrectDataException();
        platoPersistencePort.saveFoodPlate(foodPlate);
    }
    /**
     * Updates a food plate in the application. Validates if the only submitted data is the description and the price,
     * and only update this if the data is not null.
     * @param foodPlate the user to be updated.
     * @throws ForbiddenUpdateException if it's submitted different data as the specified.
     * @throws IncorrectDataException if the submitted data is invalid.
     */
    @Override
    public FoodPlate updateFoodPlate(String email, FoodPlate foodPlate) {
        FoodPlate foodPlateToUpdate = platoPersistencePort.getFoodPlate(foodPlate.getId());

        //Add business logic
        User user = userClientPort.getUserByEmail(email);
        //Verifies if is an owner
        if(user.getIdRole() != ROLES.OWNER.id){
            throw new NotAnOwnerException();
        }

        Restaurant r = restaurantPersistencePort.getRestaurant(
                foodPlateToUpdate.getIdRestaurant()   //Search the restaurant and throws an exception if it doesn't exist
        );
        if(r.getIdOwner() != user.getId()){ //The current user isn't the owner of this restaurant
            throw new NotAllowedRestaurantException();
        }


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
        if(!platoValidator.validatesPrice(foodPlateToUpdate.getPrice()))
            throw new IncorrectDataException();
        return platoPersistencePort.updateFoodPlate(foodPlateToUpdate);
    }

    /**
     * Changed the state of a food plate, to the state of the food plate submitted.
     *
     * @param foodPlate the food plate
     */
    @Override
    public void changeStateFoodPlate(FoodPlate foodPlate) {
        FoodPlate foodPlateToUpdate = platoPersistencePort.getFoodPlate(foodPlate.getId());
        foodPlateToUpdate.setActive(foodPlate.getActive());
        platoPersistencePort.updateFoodPlate(foodPlateToUpdate);
    }
}
