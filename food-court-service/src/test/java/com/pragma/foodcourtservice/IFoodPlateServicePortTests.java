package com.pragma.foodcourtservice;

import com.pragma.foodcourtservice.domain.api.IFoodPlateServicePort;
import com.pragma.foodcourtservice.domain.api.IFoodPlateValidator;
import com.pragma.foodcourtservice.domain.exception.ForbiddenUpdateException;
import com.pragma.foodcourtservice.domain.exception.IncorrectDataException;
import com.pragma.foodcourtservice.domain.exception.RestaurantNotFoundException;
import com.pragma.foodcourtservice.domain.model.FoodPlate;
import com.pragma.foodcourtservice.domain.spi.ICategoryPersistencePort;
import com.pragma.foodcourtservice.domain.spi.IFoodPlatePersistencePort;
import com.pragma.foodcourtservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourtservice.domain.useCase.FoodPlateUseCase;
import com.pragma.foodcourtservice.infrastructure.exception.CategoryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Not implemented.
 */
@SpringBootTest
public class IFoodPlateServicePortTests {
    IRestaurantPersistencePort restaurantPersistencePort;
    ICategoryPersistencePort categoryPersistencePort;
    IFoodPlatePersistencePort foodPlatePersistencePort;
    IFoodPlateValidator foodPlateValidator;
    IFoodPlateServicePort foodPlateServicePort;
    @BeforeEach
    void setUp(){
        restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        categoryPersistencePort = mock(ICategoryPersistencePort.class);
        foodPlatePersistencePort = mock(IFoodPlatePersistencePort.class);
        foodPlateValidator = mock(IFoodPlateValidator.class);
        foodPlateServicePort = new FoodPlateUseCase(restaurantPersistencePort, categoryPersistencePort, foodPlatePersistencePort,
                foodPlateValidator);
        //Mock restaurant persistence port
        when(restaurantPersistencePort
                .getRestaurant(RestaurantData.RESTAURANT_001.getId()))
                .thenReturn(RestaurantData.RESTAURANT_001);
        when(restaurantPersistencePort
                .getRestaurant(RestaurantData.NON_INSERTED_RESTAURANT.getId()))
                .thenThrow(new RestaurantNotFoundException());
        //Mock category persistence port
        when(categoryPersistencePort.getCategory(CategoryData.CATEGORY_001.getId()))
                .thenReturn(CategoryData.CATEGORY_001);
        when(categoryPersistencePort.getCategory(CategoryData.NON_INSERTED_CATEGORY.getId()))
                .thenThrow(new CategoryNotFoundException());
        //Mock foodPlateValidator
        when(foodPlateValidator.validatesPrice(FoodPlateData.VALID_FOOD_PLATE.getPrice())).thenReturn(true);
        when(foodPlateValidator
                .validatesPrice(FoodPlateData.INVALID_PRICE_FOOD_PLATE.getPrice())).thenReturn(false);
    }

    /**
     * Saving a restaurant has the following cases:
     * - If the price isn't valid, throws IncorrectDataException.
     * - If the restaurant of the food plate doesn't exist, throws RestaurantDoestExistsException.
     * - If the category of the food plate doesn't exist, throws CategoryDoesntExistException.
     * - Saves the food plate correctly.
     */
    @Test
    void saveAFoodPlate(){
        //The food plate has to save correctly.
        assertDoesNotThrow(()-> foodPlateServicePort.savePlato(FoodPlateData.VALID_FOOD_PLATE));
        //The food plate won't save because the price isn't valid.
        assertThrows(IncorrectDataException.class,
                ()-> foodPlateServicePort.savePlato(FoodPlateData.INVALID_PRICE_FOOD_PLATE));
        //The food plate won't save because the restaurant doesn't exist.
        assertThrows(RestaurantNotFoundException.class,
                ()-> foodPlateServicePort.savePlato(FoodPlateData.INVALID_RESTAURANT_FOOD_PLATE));
        //The food plate won't save because the category doesn't exist.
        assertThrows(CategoryNotFoundException.class,
                ()-> foodPlateServicePort.savePlato(FoodPlateData.INVALID_CATEGORY_FOOD_PLATE));
    }
    /**
     * Update a restaurant with a FoodPlate provided data. It charges a FoodPlate data and contrast with an FoodPlate
     * object with the same id (has to exist, in other case return FoodPlateDoesntExistException) to changes the data.
     * After do that, can occur one of the following cases:
     * - If the active state, category, restaurant is changed and is different of null,
     * throws an ForbiddenUpdateException.
     * - If the description or price isn't provided (is null), this data keeps the same.
     * - If the new price isn't valid, throw IncorrectDataException.
     * - Saves the food plate correctly.
     */
    @Test
    void updateAFoodPlate(){
        FoodPlate test = FoodPlateData.clone(FoodPlateData.FOOD_PLATE_001);
        //Ensures that the new object (test) has the same ID as FOOD_PLATE_001
        assertTrue(FoodPlateData.FOOD_PLATE_001.getId() == test.getId());
        //Ensures that FOOD_PLATE_001 exists in the persistence layer.
        when(foodPlatePersistencePort.getFoodPlate(FoodPlateData.FOOD_PLATE_001.getId()))
                .thenReturn(FoodPlateData.clone(FoodPlateData.FOOD_PLATE_001));
        //Works fine, nothing has changed.
        assertDoesNotThrow( () -> foodPlateServicePort.updatePlato(test));

        //Works fine, the changed data is null but doesn't matter.
        test.setPrice(null);
        assertDoesNotThrow( () -> foodPlateServicePort.updatePlato(test));

        //Works fine, only the description and price has changed and are valid.
        test.setDescription("LALALALALALALA");
        test.setPrice(FoodPlateData.VALID_FOOD_PLATE.getPrice());
        assertDoesNotThrow( () -> foodPlateServicePort.updatePlato(test));

        //Fails, The price isn't valid.
        test.setPrice(FoodPlateData.INVALID_PRICE_FOOD_PLATE.getPrice());
        assertThrows(IncorrectDataException.class,
                () -> foodPlateServicePort.updatePlato(test));

        //Fails, the price is OK but the idRestaurant changed.
        test.setPrice(FoodPlateData.FOOD_PLATE_001.getPrice());
        test.setIdRestaurant(RestaurantData.NON_INSERTED_RESTAURANT.getId());
        assertThrows(ForbiddenUpdateException.class,
                () -> foodPlateServicePort.updatePlato(test));
    }
}
