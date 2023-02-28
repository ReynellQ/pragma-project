package com.pragma.foodcourtservice.domain.api;

import com.pragma.foodcourtservice.RestaurantData;
import com.pragma.foodcourtservice.domain.exception.IncorrectDataException;
import com.pragma.foodcourtservice.domain.exception.NotAllowedRestaurantException;
import com.pragma.foodcourtservice.domain.exception.NotAnOwnerException;
import com.pragma.foodcourtservice.domain.model.Restaurant;
import com.pragma.foodcourtservice.domain.model.User;
import com.pragma.foodcourtservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourtservice.domain.spi.IUserMicroServiceClientPort;
import com.pragma.foodcourtservice.domain.useCase.RestaurantUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IRestaurantServicePortTest {
    IUserMicroServiceClientPort userMicroServiceClientPort;
    IRestaurantValidator restaurantValidator;
    IRestaurantPersistencePort restaurantPersistencePort;
    IPersistentLoggedUser persistentLoggedUser;
    IRestaurantServicePort restaurantServicePort;

    @BeforeEach
    void setUp(){
        restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        restaurantValidator = mock(IRestaurantValidator.class);
        userMicroServiceClientPort = mock(IUserMicroServiceClientPort.class);
        persistentLoggedUser = mock(IPersistentLoggedUser.class);
        restaurantServicePort = new RestaurantUseCase(restaurantPersistencePort, restaurantValidator,
                userMicroServiceClientPort, persistentLoggedUser);
    }

    @Test
    void saveRestaurant() {
        assertAll(
                ()->goodSave(),
                ()->savingWithAnLoggedUserNotOwner(),
                ()->savingWithBadData()
        );
    }

    private void goodSave() {
        User owner = RestaurantData.OWNER_001;
        Restaurant restaurant = RestaurantData.NON_VALID_RESTAURANT;
        when(restaurantValidator.validateOwner(owner))
                .thenReturn(true);
        when(restaurantValidator.validateName(restaurant.getName()))
                .thenReturn(true);
        when(restaurantValidator.validatePhone(restaurant.getPhone()))
                .thenReturn(true);
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(owner);
        assertDoesNotThrow( //Saves correctly
                ()->restaurantServicePort.saveRestaurant(restaurant)
        );
    }

    private void savingWithAnLoggedUserNotOwner() {
        User owner = RestaurantData.NOT_A_OWNER;
        Restaurant restaurant = RestaurantData.NON_VALID_RESTAURANT;
        when(restaurantValidator.validateOwner(owner))
                .thenReturn(false);
        when(restaurantValidator.validateName(restaurant.getName()))
                .thenReturn(true);
        when(restaurantValidator.validatePhone(restaurant.getPhone()))
                .thenReturn(true);
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(owner);
        assertThrows(
                NotAnOwnerException.class,
                ()->restaurantServicePort.saveRestaurant(restaurant)
        );
    }

    private void savingWithBadData() {
        User owner = RestaurantData.OWNER_001;
        Restaurant restaurant = RestaurantData.NON_VALID_RESTAURANT;
        when(restaurantValidator.validateOwner(owner))
                .thenReturn(true);
        when(restaurantValidator.validateName(restaurant.getName()))
                .thenReturn(false);
        when(restaurantValidator.validatePhone(restaurant.getPhone()))
                .thenReturn(false);
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(owner);
        assertThrows(
                IncorrectDataException.class,
                ()->restaurantServicePort.saveRestaurant(restaurant)
        );
    }

    @Test
    void listAllAlphabeticallyRestaurantsPaginated() {
        List<Restaurant> restaurants = new ArrayList<>(
                List.of(
                    new Restaurant(1l,"Flipopia","42219 Independence Street",2l,"5592967691","http://dummyimage.com/240x100.png/dddddd/000000",4053949l),
                    new Restaurant(2l,"Twitternation","7579 Dawn Trail",1l,"1352706724","http://dummyimage.com/168x100.png/cc0000/ffffff",2254228l),
                    new Restaurant(3l,"Gevee","3041 Acker Trail",1l,"4603276081","http://dummyimage.com/156x100.png/dddddd/000000",8155550l),
                    new Restaurant(4l,"Browsedrive","6140 Amoth Center",1l,"2596434607","http://dummyimage.com/239x100.png/dddddd/000000",8423066l),
                    new Restaurant(5l,"Oba","5 Michigan Pass",1l,"7632327514","http://dummyimage.com/186x100.png/dddddd/000000",2165456l),
                    new Restaurant(6l,"Oozz","942 Graceland Alley",2l,"5433941001","http://dummyimage.com/228x100.png/cc0000/ffffff",2623525l),
                    new Restaurant(7l,"Kayveo","5 Norway Maple Center",1l,"4155656889","http://dummyimage.com/130x100.png/cc0000/ffffff",5991537l),
                    new Restaurant(8l,"Feedfire","03468 Chinook Circle",1l,"5614270036","http://dummyimage.com/220x100.png/5fa2dd/ffffff",8447658l),
                    new Restaurant(9l,"Vipe","5 Magdeline Hill",1l,"9453874440","http://dummyimage.com/206x100.png/dddddd/000000",6361938l),
                    new Restaurant(10l,"Gabtype","02 Thackeray Hill",1l,"1815566248","http://dummyimage.com/231x100.png/ff4444/ffffff",8735957l),
                    new Restaurant(11l,"Twimm","78 Clyde Gallagher Park",1l,"1307815939","http://dummyimage.com/128x100.png/ff4444/ffffff",2707578l),
                    new Restaurant(12l,"Feednation","26 Brentwood Crossing",2l,"9512608484","http://dummyimage.com/128x100.png/dddddd/000000",5221733l),
                    new Restaurant(13l,"Leenti","4451 Union Street",2l,"9734803379","http://dummyimage.com/137x100.png/cc0000/ffffff",9718863l),
                    new Restaurant(14l,"Jabbercube","41 High Crossing Crossing",2l,"6117306543","http://dummyimage.com/175x100.png/ff4444/ffffff",5375125l),
                    new Restaurant(15l,"Midel","6031 Gateway Street",1l,"4079321420","http://dummyimage.com/142x100.png/cc0000/ffffff",2068797l),
                    new Restaurant(16l,"Centimia","85 Dakota Plaza",2l,"8686498073","http://dummyimage.com/149x100.png/dddddd/000000",7424641l),
                    new Restaurant(17l,"Kwinu","5 Evergreen Point",1l,"5314873157","http://dummyimage.com/201x100.png/5fa2dd/ffffff",3695843l),
                    new Restaurant(18l,"Dabfeed","83913 Dottie Point",2l,"2013477061","http://dummyimage.com/191x100.png/cc0000/ffffff",6147712l),
                    new Restaurant(19l,"Kwinu","66937 Red Cloud Crossing",1l,"5886012692","http://dummyimage.com/109x100.png/5fa2dd/ffffff",6340085l),
                    new Restaurant(20l,"Miboo","21 Dakota Road",1l,"6254017071","http://dummyimage.com/227x100.png/ff4444/ffffff",2124614l)
                )
        );
        User owner = RestaurantData.OWNER_001;
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(owner);
        restaurants.stream().filter( (restaurant -> restaurant.getIdOwner() == owner.getId()));
        int page = 0;
        int limit = 5;
        Collections.sort(restaurants, Comparator.comparing(Restaurant::getName));
        //Erase the restaurants for previous pages
        for(int i = 0 ; i < page*limit ; ++i){
            restaurants.remove(i);
        }
        //Erase the restaurants for posterior pages
        while(restaurants.size() > limit){
            restaurants.remove(limit);
        }
        when(restaurantPersistencePort.listAllAlphabeticallyRestaurantsPaginated(page, limit)).thenReturn(restaurants);
        assertEquals(restaurants, restaurantServicePort.listAllAlphabeticallyRestaurantsPaginated(page, limit));
    }

    @Test
    void saveAnEmployeeOfARestaurant() {
        assertAll(
                ()->goodEmployeeSave(),
                ()->userLoggedIsNotAOwner(),
                ()->userLoggedIsNotTheOwnerOfRestaurant(),
                ()->badInsertEmployee()
        );
    }

    private void goodEmployeeSave() {
        User owner = RestaurantData.OWNER_001;
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        User employee = RestaurantData.EMPLOYEE;
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(owner);
        when(restaurantValidator.validateOwner(owner))
                .thenReturn(true);
        when(restaurantPersistencePort.getRestaurant(restaurant.getId()))
                .thenReturn(restaurant);
        doNothing().when(userMicroServiceClientPort)
                .saveAnEmployee(employee);
        when(userMicroServiceClientPort.getUserByPersonalId(employee.getPersonalId()))
                .thenReturn(employee);

        assertDoesNotThrow(
                ()->restaurantServicePort.saveAnEmployeeOfARestaurant(employee, restaurant.getId())
        );
    }

    private void userLoggedIsNotAOwner() {
        User owner = RestaurantData.NOT_A_OWNER;
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        User employee = RestaurantData.EMPLOYEE;
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(owner);
        when(restaurantValidator.validateOwner(owner))
                .thenReturn(false);
        when(restaurantPersistencePort.getRestaurant(restaurant.getId()))
                .thenReturn(restaurant);
        doNothing().when(userMicroServiceClientPort)
                .saveAnEmployee(employee);
        when(userMicroServiceClientPort.getUserByPersonalId(employee.getPersonalId()))
                .thenReturn(employee);

        assertThrows(
                NotAnOwnerException.class,
                ()->restaurantServicePort.saveAnEmployeeOfARestaurant(employee, restaurant.getId())
        );
    }

    private void userLoggedIsNotTheOwnerOfRestaurant() {
        User owner = RestaurantData.OWNER_002;
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        User employee = RestaurantData.EMPLOYEE;
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(owner);
        when(restaurantValidator.validateOwner(owner))
                .thenReturn(true);
        when(restaurantPersistencePort.getRestaurant(restaurant.getId()))
                .thenReturn(restaurant);
        doNothing().when(userMicroServiceClientPort)
                .saveAnEmployee(employee);
        when(userMicroServiceClientPort.getUserByPersonalId(employee.getPersonalId()))
                .thenReturn(employee);

        assertThrows(
                NotAllowedRestaurantException.class,
                ()->restaurantServicePort.saveAnEmployeeOfARestaurant(employee, restaurant.getId())
        );
    }

    private void badInsertEmployee() {
        User owner = RestaurantData.OWNER_002;
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        User employee = RestaurantData.EMPLOYEE;
        when(persistentLoggedUser.getLoggedUser())
                .thenReturn(owner);
        when(restaurantValidator.validateOwner(owner))
                .thenReturn(true);
        when(restaurantPersistencePort.getRestaurant(restaurant.getId()))
                .thenReturn(restaurant);
        doThrow( IncorrectDataException.class ).when(userMicroServiceClientPort)
                .saveAnEmployee(employee);
        when(userMicroServiceClientPort.getUserByPersonalId(employee.getPersonalId()))
                .thenReturn(employee);

        assertThrows(
                NotAllowedRestaurantException.class,
                ()->restaurantServicePort.saveAnEmployeeOfARestaurant(employee, restaurant.getId())
        );
    }
}