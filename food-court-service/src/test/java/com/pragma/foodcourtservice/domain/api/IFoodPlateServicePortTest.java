package com.pragma.foodcourtservice.domain.api;

import com.pragma.foodcourtservice.FoodPlateData;
import com.pragma.foodcourtservice.RestaurantData;
import com.pragma.foodcourtservice.domain.exception.*;
import com.pragma.foodcourtservice.domain.model.Category;
import com.pragma.foodcourtservice.domain.model.FoodPlate;
import com.pragma.foodcourtservice.domain.model.Restaurant;
import com.pragma.foodcourtservice.domain.spi.ICategoryPersistencePort;
import com.pragma.foodcourtservice.domain.spi.IFoodPlatePersistencePort;
import com.pragma.foodcourtservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourtservice.domain.useCase.FoodPlateUseCase;
import com.pragma.foodcourtservice.infrastructure.exception.CategoryNotFoundException;
import com.pragma.foodcourtservice.infrastructure.exception.FoodPlateNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IFoodPlateServicePortTest {
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
    }

    @Test
    void saveFoodPlateCorrectly() {
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(RestaurantData.OWNER_001);
        FoodPlate foodPlate = FoodPlateData.FOOD_PLATE_001;
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Category category = new Category(foodPlate.getIdCategory(), "Categoria 1",
                "Esto es una categoria");
        when(foodPlateValidator.validatesPrice(foodPlate.getPrice()))
                .thenReturn(true);
        when(restaurantPersistencePort.getRestaurant(foodPlate.getIdRestaurant()))
                .thenReturn(restaurant);
        when(categoryPersistencePort.getCategory(foodPlate.getIdCategory()))
                .thenReturn(category);

        assertDoesNotThrow(()-> foodPlateServicePort.saveFoodPlate( foodPlate));
    }

    @Test
    void saveFoodPlateWithBadData() {
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(RestaurantData.OWNER_001);
        FoodPlate foodPlate = FoodPlateData.INVALID_PRICE_FOOD_PLATE;
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Category category = new Category(foodPlate.getIdCategory(), "Categoria 1",
                "Esto es una categoria");
        when(foodPlateValidator.validatesPrice(foodPlate.getPrice()))
                .thenReturn(false);
        when(restaurantPersistencePort.getRestaurant(foodPlate.getIdRestaurant()))
                .thenReturn(restaurant);
        when(categoryPersistencePort.getCategory(foodPlate.getIdCategory()))
                .thenReturn(category);


        assertThrows(IncorrectDataException.class,
                ()-> foodPlateServicePort.saveFoodPlate(foodPlate));
    }

    @Test
    void saveFoodPlateButRestaurantDoesNotExist() {
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(RestaurantData.OWNER_001);
        FoodPlate foodPlate = FoodPlateData.INVALID_RESTAURANT_FOOD_PLATE;
        Category category = new Category(foodPlate.getIdCategory(), "Categoria 1",
                "Esto es una categoria");
        when(foodPlateValidator.validatesPrice(foodPlate.getPrice()))
                .thenReturn(true);
        when(restaurantPersistencePort.getRestaurant(foodPlate.getIdRestaurant()))
                .thenThrow(RestaurantNotFoundException.class);
        when(categoryPersistencePort.getCategory(foodPlate.getIdCategory()))
                .thenReturn(category);
        assertThrows(RestaurantNotFoundException.class,
                ()-> foodPlateServicePort.saveFoodPlate(foodPlate));
    }

    @Test
    void saveFoodPlateButCategoryDoesNotExist() {
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(RestaurantData.OWNER_001);
        FoodPlate foodPlate = FoodPlateData.INVALID_CATEGORY_FOOD_PLATE;
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Category category = new Category(foodPlate.getIdCategory(), "Categoria 1",
                "Esto es una categoria");
        when(foodPlateValidator.validatesPrice(foodPlate.getPrice()))
                .thenReturn(true);
        when(restaurantPersistencePort.getRestaurant(foodPlate.getIdRestaurant()))
                .thenReturn(restaurant);
        when(categoryPersistencePort.getCategory(foodPlate.getIdCategory()))
                .thenThrow(CategoryNotFoundException.class);


        assertThrows(CategoryNotFoundException.class,
                ()-> foodPlateServicePort.saveFoodPlate(foodPlate));
    }

    @Test
    void saveFoodPlateRestaurantDoesNotBelongToOwner() {
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(RestaurantData.OWNER_002);
        FoodPlate foodPlate = FoodPlateData.FOOD_PLATE_001;
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Category category = new Category(foodPlate.getIdCategory(), "Categoria 1",
                "Esto es una categoria");
        when(foodPlateValidator.validatesPrice(foodPlate.getPrice()))
                .thenReturn(true);
        when(restaurantPersistencePort.getRestaurant(foodPlate.getIdRestaurant()))
                .thenReturn(restaurant);
        when(categoryPersistencePort.getCategory(foodPlate.getIdCategory()))
                .thenReturn(category);


        assertThrows(NotAllowedRestaurantException.class,
                ()-> foodPlateServicePort.saveFoodPlate(foodPlate));
    }

    @Test
    void saveFoodPlateNotAnOwner() {
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(RestaurantData.EMPLOYEE);
        FoodPlate foodPlate = FoodPlateData.FOOD_PLATE_001;
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        Category category = new Category(foodPlate.getIdCategory(), "Categoria 1",
                "Esto es una categoria");
        when(foodPlateValidator.validatesPrice(foodPlate.getPrice()))
                .thenReturn(true);
        when(restaurantPersistencePort.getRestaurant(foodPlate.getIdRestaurant()))
                .thenReturn(restaurant);
        when(categoryPersistencePort.getCategory(foodPlate.getIdCategory()))
                .thenReturn(category);


        assertThrows(NotAnOwnerException.class,
                ()-> foodPlateServicePort.saveFoodPlate(foodPlate));
    }

    @Test
    void updateDataCorrectly() {
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(RestaurantData.OWNER_001);
        FoodPlate foodPlate = FoodPlateData.clone(FoodPlateData.FOOD_PLATE_001);
        FoodPlate foodPlateToUpdate = FoodPlateData.clone(FoodPlateData.FOOD_PLATE_001);
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        when(restaurantPersistencePort.getRestaurant(restaurant.getId()))
                .thenReturn(restaurant);
        String newDescrption = null; //Can be null
        Long newPrice = null; //Can be null
        foodPlate.setDescription(newDescrption);
        foodPlate.setPrice(newPrice);
        foodPlateToUpdate.setDescription( newDescrption != null ?
                newDescrption : foodPlateToUpdate.getDescription());
        foodPlateToUpdate.setPrice( newPrice != null ?
                newPrice : foodPlateToUpdate.getPrice());
        when(foodPlateValidator.validatesPrice(foodPlateToUpdate.getPrice()))
                .thenReturn(true); //If the newPrice is null, it doesn't update.

        when(foodPlatePersistencePort.getFoodPlate(FoodPlateData.FOOD_PLATE_001.getId()))
                .thenReturn(foodPlateToUpdate);
        when(foodPlatePersistencePort.updateFoodPlate(foodPlateToUpdate))
                .thenReturn(foodPlateToUpdate);
        assertEquals(foodPlateToUpdate, foodPlateServicePort.updateFoodPlate(foodPlate));
    }

    @Test
    void updateWithIncorrectData() {
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(RestaurantData.OWNER_001);
        FoodPlate foodPlate = FoodPlateData.clone(FoodPlateData.FOOD_PLATE_001);
        FoodPlate foodPlateToUpdate = FoodPlateData.clone(FoodPlateData.FOOD_PLATE_001);
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        when(restaurantPersistencePort.getRestaurant(restaurant.getId()))
                .thenReturn(restaurant);
        String newDescription = null; //Can be null
        Long newPrice = -1L; //A bad value
        foodPlate.setDescription(newDescription);
        foodPlate.setPrice(newPrice);
        foodPlateToUpdate.setDescription( newDescription != null ?
                newDescription : foodPlateToUpdate.getDescription());
        foodPlateToUpdate.setPrice( newPrice != null ?
                newPrice : foodPlateToUpdate.getPrice());
        when(foodPlateValidator.validatesPrice(foodPlateToUpdate.getPrice()))
                .thenReturn(false);
        when(foodPlatePersistencePort.getFoodPlate(foodPlateToUpdate.getId()))
                .thenReturn(foodPlateToUpdate);

        assertThrows(
                IncorrectDataException.class,
                ()->foodPlateServicePort.updateFoodPlate(foodPlate)
        );
    }

    @Test
    void updateForbiddenData() {
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(RestaurantData.OWNER_001);
        FoodPlate foodPlate = FoodPlateData.clone(FoodPlateData.FOOD_PLATE_001);
        FoodPlate foodPlateToUpdate = FoodPlateData.clone(FoodPlateData.FOOD_PLATE_001);
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        when(restaurantPersistencePort.getRestaurant(restaurant.getId()))
                .thenReturn(restaurant);
        foodPlate.setIdCategory(3l); //This is forbidden.

        when(foodPlatePersistencePort.getFoodPlate(foodPlateToUpdate.getId()))
                .thenReturn(foodPlateToUpdate);

        assertThrows(
                ForbiddenUpdateException.class,
                ()->foodPlateServicePort.updateFoodPlate(foodPlate)
        );
    }

    @Test
    void updateNonExistingFoodPlate() {
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(RestaurantData.OWNER_001);
        FoodPlate foodPlate = FoodPlateData.clone(FoodPlateData.FOOD_PLATE_001);
        FoodPlate foodPlateToUpdate = FoodPlateData.clone(FoodPlateData.FOOD_PLATE_001);
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        when(restaurantPersistencePort.getRestaurant(restaurant.getId()))
                .thenReturn(restaurant);
        foodPlate.setIdCategory(3L); //This is forbidden.

        when(foodPlatePersistencePort.getFoodPlate(foodPlate.getId()))
                .thenThrow(FoodPlateNotFoundException.class);

        assertThrows(
                FoodPlateNotFoundException.class,
                ()->foodPlateServicePort.updateFoodPlate(foodPlate)
        );
    }

    @Test
    void updateFoodPlateWithAnNonOwnerUser() {
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(RestaurantData.EMPLOYEE);
        FoodPlate foodPlate = FoodPlateData.clone(FoodPlateData.FOOD_PLATE_001);
        FoodPlate foodPlateToUpdate = FoodPlateData.clone(FoodPlateData.FOOD_PLATE_001);
        when(foodPlatePersistencePort.getFoodPlate(foodPlateToUpdate.getId()))
                .thenReturn(foodPlateToUpdate);
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        when(restaurantPersistencePort.getRestaurant(restaurant.getId()))
                .thenReturn(restaurant);

        assertThrows(
                NotAnOwnerException.class,
                ()->foodPlateServicePort.updateFoodPlate(foodPlate)
        );
    }

    @Test
    void updateAFoodPlateThatDoesNotBelongToOwner() {
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(RestaurantData.OWNER_002);
        FoodPlate foodPlate = FoodPlateData.clone(FoodPlateData.FOOD_PLATE_001);
        FoodPlate foodPlateToUpdate = FoodPlateData.clone(FoodPlateData.FOOD_PLATE_001);
        when(foodPlatePersistencePort.getFoodPlate(foodPlateToUpdate.getId()))
                .thenReturn(foodPlateToUpdate);
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        when(restaurantPersistencePort.getRestaurant(restaurant.getId()))
                .thenReturn(restaurant);

        when(foodPlatePersistencePort.getFoodPlate(foodPlate.getId()))
                .thenReturn(foodPlateToUpdate);

        assertThrows(
                NotAllowedRestaurantException.class,
                ()->foodPlateServicePort.updateFoodPlate(foodPlate)
        );
    }

    @Test
    void goodChangeState() {
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(RestaurantData.OWNER_001);
        Boolean newStatus = true;
        FoodPlate foodPlate = FoodPlateData.clone(FoodPlateData.FOOD_PLATE_001);
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        foodPlate.setActive(newStatus);
        when(foodPlatePersistencePort.getFoodPlate(foodPlate.getId()))
                .thenReturn(foodPlate);
        when(restaurantPersistencePort.getRestaurant(foodPlate.getId()))
                .thenReturn(restaurant);
        assertDoesNotThrow(
                ()-> foodPlateServicePort.changeStateFoodPlate(foodPlate)
        );
    }

    @Test
    void changeStateAndLoggedUserIsNotOwner() {
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(RestaurantData.EMPLOYEE);
        Boolean newStatus = true;
        FoodPlate foodPlate = FoodPlateData.clone(FoodPlateData.FOOD_PLATE_001);
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        when(restaurantPersistencePort.getRestaurant(foodPlate.getId()))
                .thenReturn(restaurant);
        foodPlate.setActive(newStatus);
        when(foodPlatePersistencePort.getFoodPlate(foodPlate.getId()))
                .thenReturn(foodPlate);
        assertThrows(
                NotAnOwnerException.class,
                ()-> foodPlateServicePort.changeStateFoodPlate(foodPlate)
        );
    }

    @Test
    void changeStateAndOwnerDoesNotOwnsRestaurant() {
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(RestaurantData.OWNER_002);
        Boolean newStatus = true;
        FoodPlate foodPlate = FoodPlateData.clone(FoodPlateData.FOOD_PLATE_001);
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        when(restaurantPersistencePort.getRestaurant(foodPlate.getId()))
                .thenReturn(restaurant);
        foodPlate.setActive(newStatus);
        when(foodPlatePersistencePort.getFoodPlate(foodPlate.getId()))
                .thenReturn(foodPlate);
        assertThrows(
                NotAllowedRestaurantException.class,
                ()-> foodPlateServicePort.changeStateFoodPlate(foodPlate)
        );
    }

    @Test
    void listTheFoodPlatesByCategory() {
        List<FoodPlate> foodPlates = new ArrayList<>(
                List.of(
                        new FoodPlate(1l,"Phalacrocorax varius",3l,null,"Schiller Group",7872l,1l,"http://dummyimage.com/114x100.png/5fa2dd/ffffff",true),
                        new FoodPlate(2l,"Larus fuliginosus",1l,null,"Altenwerth Group",7183l,2l,"http://dummyimage.com/122x100.png/dddddd/000000",false),
                        new FoodPlate(3l,"Marmota monax",3l,null,"Ebert-Gleason",9073l,1l,"http://dummyimage.com/240x100.png/dddddd/000000",false),
                        new FoodPlate(4l,"Corvus albus",2l,null,"Kassulke-Gutmann",7019l,2l,"http://dummyimage.com/101x100.png/cc0000/ffffff",false),
                        new FoodPlate(5l,"Lasiodora parahybana",1l,null,"Skiles, Monahan and Effertz",5766l,2l,"http://dummyimage.com/174x100.png/dddddd/000000",false),
                        new FoodPlate(6l,"Nucifraga columbiana",1l,null,"Monahan-Wilkinson",5896l,1l,"http://dummyimage.com/138x100.png/dddddd/000000",true),
                        new FoodPlate(7l,"Sula dactylatra",1l,null,"Trantow and Sons",4554l,1l,"http://dummyimage.com/172x100.png/ff4444/ffffff",false),
                        new FoodPlate(8l,"Ursus americanus",3l,null,"Kris, Armstrong and Nolan",1766l,1l,"http://dummyimage.com/225x100.png/5fa2dd/ffffff",false),
                        new FoodPlate(9l,"Amblyrhynchus cristatus",3l,null,"Cormier, Rohan and Moore",9533l,1l,"http://dummyimage.com/213x100.png/ff4444/ffffff",false),
                        new FoodPlate(10l,"Parus atricapillus",1l,null,"Zemlak, Schroeder and Wilderman",8899l,1l,"http://dummyimage.com/162x100.png/cc0000/ffffff",true),
                        new FoodPlate(11l,"Rhea americana",2l,null,"Emmerich-Schowalter",1713l,1l,"http://dummyimage.com/168x100.png/ff4444/ffffff",false),
                        new FoodPlate(12l,"Actophilornis africanus",2l,null,"Ryan Inc",2403l,2l,"http://dummyimage.com/130x100.png/5fa2dd/ffffff",false),
                        new FoodPlate(13l,"Anastomus oscitans",2l,null,"Kautzer, Lind and Leannon",8365l,2l,"http://dummyimage.com/152x100.png/ff4444/ffffff",true),
                        new FoodPlate(14l,"Alcelaphus buselaphus cokii",2l,null,"Johns-MacGyver",5677l,2l,"http://dummyimage.com/190x100.png/dddddd/000000",true),
                        new FoodPlate(15l,"Colaptes campestroides",2l,null,"Carter Group",8140l,1l,"http://dummyimage.com/100x100.png/dddddd/000000",true),
                        new FoodPlate(16l,"Ictalurus furcatus",3l,null,"Reinger, Bechtelar and Grant",4123l,2l,"http://dummyimage.com/120x100.png/dddddd/000000",false),
                        new FoodPlate(17l,"Choloepus hoffmani",1l,null,"Kuhn, Fahey and Treutel",6340l,1l,"http://dummyimage.com/194x100.png/cc0000/ffffff",true),
                        new FoodPlate(18l,"Laniaurius atrococcineus",1l,null,"Christiansen, Mills and Gleichner",2963l,2l,"http://dummyimage.com/176x100.png/ff4444/ffffff",false),
                        new FoodPlate(19l,"Ovis ammon",2l,null,"Jacobi-McClure",4607l,1l,"http://dummyimage.com/248x100.png/cc0000/ffffff",true),
                        new FoodPlate(20l,"Spermophilus armatus",3l,null,"Mitchell, Wunsch and Thompson",5700l,2l,"http://dummyimage.com/104x100.png/dddddd/000000",true)
                )
        );
        Long idRestaurant = 1l;
        List<Long> categories = List.of(1L, 2L);
        int page = 1;
        int number = 10;
        if(!categories.isEmpty()){
            foodPlates.stream()
                    .filter(
                            (foodPlate -> Set.of(categories).contains(foodPlate.getIdCategory()))
                    );
        }
        for(int i = 0 ; i < page*number ; ++i){
            foodPlates.remove(0);
        }
        while(foodPlates.size() > number){
            foodPlates.remove(number);
        }
        when(foodPlatePersistencePort.listTheFoodPlatesByCategory(idRestaurant, categories, page, number))
                .thenReturn(foodPlates);
        when(foodPlateServicePort.listTheFoodPlatesByCategory(idRestaurant, categories, page, number))
                .thenReturn(foodPlates);
    }
}