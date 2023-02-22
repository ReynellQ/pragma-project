package com.pragma.foodcourtservice.domain.useCase;


import com.pragma.foodcourtservice.domain.api.IRestaurantServicePort;
import com.pragma.foodcourtservice.domain.api.IRestaurantValidator;
import com.pragma.foodcourtservice.domain.exception.IncorrectDataException;
import com.pragma.foodcourtservice.domain.exception.NotAllowedRestaurantException;
import com.pragma.foodcourtservice.domain.exception.NotAnOwnerException;
import com.pragma.foodcourtservice.domain.model.Restaurant;
import com.pragma.foodcourtservice.domain.model.RestaurantEmployee;
import com.pragma.foodcourtservice.domain.model.User;
import com.pragma.foodcourtservice.domain.spi.IUserMicroServiceClientPort;
import com.pragma.foodcourtservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IRestaurantEmployeeRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * Class RestaurantUseCase that implements the interface IRestaurantServicePort, and defines the business logic that be
 * used by the API.
 */
@RequiredArgsConstructor
public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IRestaurantValidator restaurantValidator;
    private final IUserMicroServiceClientPort userClientPort;

    /**
     * Saves an owner in the application, checking first their data. It checks the name, the phone and if it's owner,
     * represented by their email, has the "Owner" role. Throws one of two exceptions if the data doesn't pass
     * the validation.
     * @param emailCreator the email of the user that calls the service.
     * @param restaurant the restaurant to the saved.
     * @throws IncorrectDataException
     * @throws NotAnOwnerException
     */
    @Override
    public void saveRestaurant(String emailCreator, Restaurant restaurant) {
        User owner = userClientPort.getUserByEmail(emailCreator);
        restaurant.setIdOwner(owner.getId());
        if(!restaurantValidator.validateOwner(owner))
            throw new NotAnOwnerException();
        if(!restaurantValidator.validatePhone(restaurant.getPhone()))
            throw new IncorrectDataException();
        if(!restaurantValidator.validateName(restaurant.getName()))
            throw new IncorrectDataException();
        restaurantPersistencePort.saveRestaurant(restaurant);
    }

    /**
     * List an amount of restaurants corresponding to the <code>numberOfRestaurants</code>, sorted alphabetically, and
     * displaying the number of page provided.
     *
     * @param numberOfRestaurants the number of restaurants displayed in the current page
     * @param page               the number of the page
     * @return the list of restaurants.
     */
    @Override
    public List<Restaurant> listAllAlphabeticallyRestaurantsPaginated(int page, int numberOfRestaurants) {
        return restaurantPersistencePort.listAllAlphabeticallyRestaurantsPaginated(numberOfRestaurants, page);
    }

    /**
     * Saves an employee in the application.
     *
     * @param emailCreator the creator's email
     * @param employee     the employee user to register.
     * @param idRestaurant the restaurant of the owner/creator, and the restaurant where the employee works.
     *
     * @throws NotAllowedRestaurantException if the restaurant owner and the owner logged isn't the same.
     */
    @Override
    public void saveAnEmployeeOfARestaurant(String emailCreator, User employee, Long idRestaurant) {
        User owner = userClientPort.getUserByEmail(emailCreator);
        Restaurant r = restaurantPersistencePort.getRestaurant(idRestaurant);
        if(r.getIdOwner() != owner.getId()){
            throw new NotAllowedRestaurantException();
        }
        userClientPort.saveAnEmployee(employee);
        //If It's saved, can be got.
        employee = userClientPort.getUserByPersonalId(employee.getPersonalId());
        RestaurantEmployee restaurantEmployee = new RestaurantEmployee();
        restaurantEmployee.setIdRestaurant(r.getId());
        restaurantEmployee.setIdUser(employee.getId());
        restaurantPersistencePort.registerAnEmployee(restaurantEmployee);

    }


}
