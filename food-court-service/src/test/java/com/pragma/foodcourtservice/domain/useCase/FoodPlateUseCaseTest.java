package com.pragma.foodcourtservice.domain.useCase;

import com.pragma.foodcourtservice.CategoryData;
import com.pragma.foodcourtservice.FoodPlateData;
import com.pragma.foodcourtservice.RestaurantData;
import com.pragma.foodcourtservice.domain.api.IFoodPlateServicePort;
import com.pragma.foodcourtservice.domain.api.IFoodPlateValidator;
import com.pragma.foodcourtservice.domain.api.IPersistentLoggedUser;
import com.pragma.foodcourtservice.domain.exception.*;
import com.pragma.foodcourtservice.domain.model.FoodPlate;
import com.pragma.foodcourtservice.domain.spi.ICategoryPersistencePort;
import com.pragma.foodcourtservice.domain.spi.IFoodPlatePersistencePort;
import com.pragma.foodcourtservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourtservice.domain.spi.IUserMicroServiceClientPort;
import com.pragma.foodcourtservice.infrastructure.exception.CategoryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FoodPlateUseCaseTest {
    IRestaurantPersistencePort restaurantPersistencePort;
    ICategoryPersistencePort categoryPersistencePort;
    IFoodPlatePersistencePort foodPlatePersistencePort;
    IFoodPlateValidator foodPlateValidator;
    IPersistentLoggedUser persistentLoggedUser;

    IFoodPlateServicePort foodPlateServicePort;
    @BeforeEach
    void setUp(){
        restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        categoryPersistencePort = mock(ICategoryPersistencePort.class);
        foodPlatePersistencePort = mock(IFoodPlatePersistencePort.class);
        foodPlateValidator = mock(IFoodPlateValidator.class);
        persistentLoggedUser = mock(IPersistentLoggedUser.class);
        foodPlateServicePort = new FoodPlateUseCase(restaurantPersistencePort, categoryPersistencePort,
                foodPlatePersistencePort, foodPlateValidator, persistentLoggedUser);
        //Mock restaurant persistence port
        mockRestaurantPersistencePort();
        //Mock category persistence port
        mockCategoryPersistencePort();
        //Mock foodPlateValidator
        when(foodPlateValidator.validatesPrice(FoodPlateData.VALID_FOOD_PLATE.getPrice())).thenReturn(true);
        when(foodPlateValidator
                .validatesPrice(FoodPlateData.INVALID_PRICE_FOOD_PLATE.getPrice())).thenReturn(false);
    }


    void mockRestaurantPersistencePort(){
        //Mock restaurant persistence port
        when(restaurantPersistencePort
                .getRestaurant(RestaurantData.RESTAURANT_001.getId()))
                .thenReturn(RestaurantData.RESTAURANT_001);
        when(restaurantPersistencePort
                .getRestaurant(RestaurantData.NON_INSERTED_RESTAURANT.getId()))
                .thenThrow(new RestaurantNotFoundException());
    }

    void mockCategoryPersistencePort(){
        when(categoryPersistencePort.getCategory(CategoryData.CATEGORY_001.getId()))
                .thenReturn(CategoryData.CATEGORY_001);
        when(categoryPersistencePort.getCategory(CategoryData.NON_INSERTED_CATEGORY.getId()))
                .thenThrow(new CategoryNotFoundException());
    }

    /**
     * Saving a restaurant has the following cases:
     * <br>
     * <br>
     * - If the price isn't valid, throws IncorrectDataException.
     * <br>
     * - If the restaurant of the food plate doesn't exist, throws RestaurantDoestExistsException.
     * <br>
     * - If the restaurant exists, but the creator of the food plate isn't the owner of the restaurant, throws
     *      NotAllowedRestaurantException
     *  <br>
     * - If the category of the food plate doesn't exist, throws CategoryDoesntExistException.
     * <br>
     * - Saves the food plate correctly.
     *
     * Technically, the email of the creator may not correspond to an actual user, but that it's previously checked
     * with the extraction of the JWT, so it's not implemented here.
     */
    @Test
    void saveFoodPlate() {

    }

    @Test
    void updateFoodPlate() {
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(RestaurantData.OWNER_001);
        FoodPlate test = FoodPlateData.clone(FoodPlateData.FOOD_PLATE_001);
        //Ensures that the new object (test) has the same ID as FOOD_PLATE_001
        assertTrue(FoodPlateData.FOOD_PLATE_001.getId() == test.getId());
        //Ensures that FOOD_PLATE_001 exists in the persistence layer.
        when(foodPlatePersistencePort.getFoodPlate(FoodPlateData.FOOD_PLATE_001.getId()))
                .thenReturn(FoodPlateData.clone(FoodPlateData.FOOD_PLATE_001));
        //Works fine, nothing has changed.
        assertDoesNotThrow( () -> foodPlateServicePort.updateFoodPlate(test));

        //Works fine, the price is null but doesn't matter.
        test.setPrice(null);
        assertDoesNotThrow( () -> foodPlateServicePort.updateFoodPlate(test));

        //Works fine, only the description and price has changed and are valid.
        test.setDescription("LALALALALALALA");
        test.setPrice(FoodPlateData.VALID_FOOD_PLATE.getPrice());
        assertDoesNotThrow( () -> foodPlateServicePort.updateFoodPlate(test));

        //Fails, The price isn't valid.
        test.setPrice(FoodPlateData.INVALID_PRICE_FOOD_PLATE.getPrice());
        assertThrows(IncorrectDataException.class,
                () -> foodPlateServicePort.updateFoodPlate(test));

        //Fails, the price is OK but the idRestaurant changed.
        test.setPrice(FoodPlateData.FOOD_PLATE_001.getPrice());
        test.setIdRestaurant(RestaurantData.NON_INSERTED_RESTAURANT.getId());
        assertThrows(ForbiddenUpdateException.class,
                () -> foodPlateServicePort.updateFoodPlate(test));

        //The food plate won't update because the owner isn't the owner of the restaurant
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(RestaurantData.OWNER_002);
        test.setIdRestaurant(RestaurantData.RESTAURANT_001.getId());
        assertThrows(NotAllowedRestaurantException.class,
                () -> foodPlateServicePort.updateFoodPlate(test));

        //The user signed isn't a owner. It won't ever happen, but is validated.
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(RestaurantData.EMPLOYEE);
        assertThrows(NotAnOwnerException.class,
                () -> foodPlateServicePort.updateFoodPlate(test));
    }

    @Test
    void changeStateFoodPlate() {

    }

}