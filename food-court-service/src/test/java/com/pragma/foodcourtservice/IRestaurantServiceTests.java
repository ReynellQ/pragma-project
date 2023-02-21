package com.pragma.foodcourtservice;

import com.pragma.foodcourtservice.domain.api.IRestaurantServicePort;
import com.pragma.foodcourtservice.domain.api.IRestaurantValidator;
import com.pragma.foodcourtservice.domain.exception.IncorrectDataException;
import com.pragma.foodcourtservice.domain.exception.NotAnOwnerException;
import com.pragma.foodcourtservice.domain.model.Restaurant;
import com.pragma.foodcourtservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourtservice.domain.spi.IUserMicroServiceClientPort;
import com.pragma.foodcourtservice.domain.useCase.RestaurantUseCase;
import com.pragma.foodcourtservice.infrastructure.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class IRestaurantServiceTests {
    IUserMicroServiceClientPort userMicroServiceClientPort;
    IRestaurantValidator restaurantValidator;
    IRestaurantPersistencePort restaurantPersistencePort;
    IRestaurantServicePort restaurantServicePort;

    @BeforeEach
    void setUp(){
        restaurantPersistencePort = mock(IRestaurantPersistencePort.class);
        restaurantValidator = mock(IRestaurantValidator.class);
        userMicroServiceClientPort = mock(IUserMicroServiceClientPort.class);
        restaurantServicePort = new RestaurantUseCase(restaurantPersistencePort, restaurantValidator,
                userMicroServiceClientPort);

        //mock data
        setUpMockValidation();
        setUpClient();

    }

    private void setUpClient() {
        when(userMicroServiceClientPort.getUserByEmail(RestaurantData.OWNER_001.getEmail()))
                .thenReturn(RestaurantData.OWNER_001);
        when(userMicroServiceClientPort.getUserByEmail(RestaurantData.NOT_A_OWNER.getEmail()))
                .thenReturn(RestaurantData.NOT_A_OWNER);
        when(userMicroServiceClientPort.getUserByEmail(UserData.NON_INSERTED_USER_001.getEmail()))
                .thenThrow(new UserNotFoundException());
    }

    private void setUpMockValidation() {
        when(restaurantValidator.validateOwner(RestaurantData.OWNER_001)).thenReturn(true);
        when(restaurantValidator.validateOwner(RestaurantData.NOT_A_OWNER)).thenReturn(false);
        when(restaurantValidator.validateName(RestaurantData.NON_INSERTED_RESTAURANT.getName()))
                .thenReturn(true);
        when(restaurantValidator.validateName(RestaurantData.NON_VALID_RESTAURANT.getName()))
                .thenReturn(false);
        when(restaurantValidator.validatePhone(RestaurantData.NON_INSERTED_RESTAURANT.getPhone()))
                .thenReturn(true);
        when(restaurantValidator.validateName(RestaurantData.NON_VALID_RESTAURANT.getPhone()))
                .thenReturn(false);
    }

    /**
     * Saving a restaurant has the following cases:
     * - If the owner doesn't exist, throws an UserDoesntExistsException
     * - If the owner isn't really an owner (doesn't have the Owner role) throws an NotAnOwnerException
     * - If the name or the phone aren't valid, throws an IncorrectDataException
     * - In other case, the restaurant saves correctly.
     *
     * This test handle all the cases.
     */
    @Test
    void saveARestaurant() {
        assertDoesNotThrow( //Saves correctly
                ()->restaurantServicePort.saveRestaurant(RestaurantData.OWNER_001.getEmail(),
                        RestaurantData.NON_INSERTED_RESTAURANT)
        );
        //Has a bad owner and bad data.
        Restaurant r = RestaurantData.NON_VALID_RESTAURANT;
        assertThrows(UserNotFoundException.class,
                () -> restaurantServicePort.saveRestaurant(UserData.NON_INSERTED_USER_001.getEmail(),
                        r)
        );
        //Modifying the email to an existing user, but not an owner.
        assertThrows(NotAnOwnerException.class, //Throws the exception for not being a owner
                () -> restaurantServicePort.saveRestaurant(RestaurantData.NOT_A_OWNER.getEmail(),r)
        );
        //Modifying the idOwner to a real owner
        assertThrows(IncorrectDataException.class,
                () -> restaurantServicePort.saveRestaurant(RestaurantData.OWNER_001.getEmail(), r)
        );
    }
}
