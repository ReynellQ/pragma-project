package com.pragma.foodcourtservice.domain.useCase;

import com.pragma.foodcourtservice.domain.api.IFoodPlateServicePort;
import com.pragma.foodcourtservice.domain.api.IFoodPlateValidator;
import com.pragma.foodcourtservice.domain.exception.IncorrectDataException;
import com.pragma.foodcourtservice.domain.exception.ForbiddenUpdateException;
import com.pragma.foodcourtservice.domain.model.FoodPlate;
import com.pragma.foodcourtservice.domain.spi.IFoodPlatePersistencePort;

/**
 * Class FoodPlateUseCase that implements the interface IFoodPlateServicePort, and defines the business logic that be
 * used by the API.
 */
public class FoodPlateUseCase implements IFoodPlateServicePort {
    private final IFoodPlatePersistencePort platoPersistencePort;
    private final IFoodPlateValidator platoValidator;

    public FoodPlateUseCase(IFoodPlatePersistencePort platoPersistencePort, IFoodPlateValidator platoValidator) {
        this.platoPersistencePort = platoPersistencePort;
        this.platoValidator = platoValidator;
    }
    /**
     * Saves a food plate in the application. Validates if the id of the restaurant belongs to an existing restaurant
     * and if the price of the food plate is valid.
     * @param foodPlate the user to be saved.
     * @throws IncorrectDataException if one of the validations doesn't pass.
     */
    @Override
    public void savePlato(FoodPlate foodPlate) {
        if(!platoValidator.validatesRestaurant(foodPlate.getIdRestaurant()))
            throw new IncorrectDataException();
        if(!platoValidator.validatesPrice(foodPlate.getPrice()))
            throw new IncorrectDataException();
        platoPersistencePort.saveFoodPlate(foodPlate);
    }
    /**
     * Updates a food plate in the application. Validates if the only submitted data is the description and the price,
     * and only update this if the data is not null.
     * @param foodPlate the user to be updated.
     * @throws ForbiddenUpdateException if it's submitted different data as the specified.
     */
    @Override
    public FoodPlate updatePlato(FoodPlate foodPlate) {
        FoodPlate platoToUpdate = platoPersistencePort.getFoodPlate(foodPlate.getId());
        //No puede cambiar el estado de activo
        if(!platoToUpdate.getActive().equals(foodPlate.getActive()) && foodPlate.getActive()!=null)
            throw new ForbiddenUpdateException();
        //No puede cambiar la categoria
        if(!platoToUpdate.getIdCategory().equals(foodPlate.getIdCategory()) && foodPlate.getIdCategory() !=null)
            throw new ForbiddenUpdateException();
        //No puede cambiar el restaurante
        if(!platoToUpdate.getIdRestaurant().equals(foodPlate.getIdRestaurant()) && foodPlate.getIdRestaurant()!=null)
            throw new ForbiddenUpdateException();
        //Solo actualizamos la descripcion si no es nulo
        if(foodPlate.getDescription() != null)
            platoToUpdate.setDescription(foodPlate.getDescription());
        //Solo actualizamos el precio si no es nulo
        if(foodPlate.getPrice() != null)
            platoToUpdate.setPrice(foodPlate.getPrice());
        return platoPersistencePort.updateFoodPlate(platoToUpdate);
    }
}
