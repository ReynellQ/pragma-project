package com.pragma.foodcourtservice.domain.spi;

import com.pragma.foodcourtservice.RestaurantData;
import com.pragma.foodcourtservice.domain.exception.RestaurantNotFoundException;
import com.pragma.foodcourtservice.domain.model.Restaurant;
import com.pragma.foodcourtservice.domain.model.RestaurantEmployee;
import com.pragma.foodcourtservice.domain.model.User;
import com.pragma.foodcourtservice.infrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.foodcourtservice.infrastructure.exception.WorkplaceNotFoundException;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.RestaurantEmployeeEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.pk.RestaurantEmployeeEntityID;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.RestaurantEmployeeEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IRestaurantEmployeeRepository;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IRestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IRestaurantPersistencePortTest {
    IRestaurantRepository restaurantRepository;
    IRestaurantEmployeeRepository restaurantEmployeeRepository;
    RestaurantEntityMapper restaurantEntityMapper;
    RestaurantEmployeeEntityMapper restaurantEmployeeEntityMapper;

    IRestaurantPersistencePort restaurantPersistencePort;

    @BeforeEach
    void setUp(){
        restaurantRepository = mock(IRestaurantRepository.class);
        restaurantEmployeeRepository = mock(IRestaurantEmployeeRepository.class);
        restaurantEntityMapper = mock(RestaurantEntityMapper.class);
        restaurantEmployeeEntityMapper = mock(RestaurantEmployeeEntityMapper.class);

        restaurantPersistencePort = new RestaurantJpaAdapter(restaurantRepository, restaurantEmployeeRepository,
                restaurantEntityMapper, restaurantEmployeeEntityMapper);
    }

    @Test
    void saveRestaurant() {
        Restaurant restaurant = RestaurantData.NON_INSERTED_RESTAURANT;
        RestaurantEntity restaurantEntity = new RestaurantEntity(restaurant.getId(), restaurant.getName(),
                restaurant.getAddress(), restaurant.getIdOwner(), restaurant.getPhone(), restaurant.getUrlLogo(),
                restaurant.getNit());
        when(restaurantEntityMapper.toEntity(restaurant))
                .thenReturn(restaurantEntity);
        assertDoesNotThrow(
                ()-> restaurantPersistencePort.saveRestaurant(restaurant)
        );
    }

    @Test
    void getRestaurantCorrectly() {
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        RestaurantEntity restaurantEntity = new RestaurantEntity(restaurant.getId(), restaurant.getName(),
                restaurant.getAddress(), restaurant.getIdOwner(), restaurant.getPhone(), restaurant.getUrlLogo(),
                restaurant.getNit());
        when(restaurantRepository.findById(restaurant.getId()))
                .thenReturn(Optional.of(restaurantEntity));
        when(restaurantEntityMapper.toRestaurant(restaurantEntity))
                .thenReturn(restaurant);
        assertEquals(restaurant,restaurantPersistencePort.getRestaurant(restaurant.getId()));
    }

    @Test
    void getRestaurantButDoesNotExist() {
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        RestaurantEntity restaurantEntity = new RestaurantEntity(restaurant.getId(), restaurant.getName(),
                restaurant.getAddress(), restaurant.getIdOwner(), restaurant.getPhone(), restaurant.getUrlLogo(),
                restaurant.getNit());
        when(restaurantRepository.findById(restaurant.getId()))
                .thenReturn(Optional.empty());
        assertThrows(
                RestaurantNotFoundException.class,
                ()->restaurantPersistencePort.getRestaurant(restaurant.getId()));
    }

    @Test
    void listAllAlphabeticallyRestaurantsPaginated() {
        List<Restaurant> restaurants = new ArrayList<>(
                List.of(
                        new Restaurant(1l,"Flipopia","42219 Independence Street",2l,"5592967691","http://dummyimage.com/240x100.png/dddddd/000000",4053949l),
                        new Restaurant(2l,"Twitternation","7579 Dawn Trail",1l,"1352706724","http://dummyimage.com/168x100.png/cc0000/ffffff",2254228l),
                        new Restaurant(3l,"Gevee","3041 Acker Trail",1l,"4603276081","http://dummyimage.com/156x100.png/dddddd/000000",8155550l),
                        new Restaurant(4l,"Browsedrive","6140 Amoth Center",1l,"2596434607","http://dummyimage.com/239x100.png/dddddd/000000",8423066l),
                        new Restaurant(5l,"Oba","5 Michigan Pass",1l,"7632327514","http://dummyimage.com/186x100.png/dddddd/000000",2165456l)
                )
        );
        List<RestaurantEntity> restaurantEntities = new ArrayList<>();
        for(int i = 0 ; i < restaurants.size() ; ++i){
            Restaurant restaurant = restaurants.get(i);
            restaurantEntities.add(
                    new RestaurantEntity(restaurant.getId(), restaurant.getName(),
                            restaurant.getAddress(), restaurant.getIdOwner(), restaurant.getPhone(), restaurant.getUrlLogo(),
                            restaurant.getNit())
            );
            when(restaurantEntityMapper.toRestaurant(restaurantEntities.get(i)))
                    .thenReturn(restaurants.get(i));
        }
        int page = 0;
        int limit = 20;
        when(restaurantRepository.findAllSortedByNameAndPaginated(page*limit, limit)).thenReturn(restaurantEntities);
        assertEquals(restaurants, restaurantPersistencePort.listAllAlphabeticallyRestaurantsPaginated(page, limit));
    }

    @Test
    void registerAnEmployee() {
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        User employee = RestaurantData.EMPLOYEE;
        RestaurantEmployee restaurantEmployee = new RestaurantEmployee( restaurant.getId(), employee.getId() );
        RestaurantEmployeeEntity restaurantEmployeeEntity = new RestaurantEmployeeEntity(
                new RestaurantEmployeeEntityID(restaurant.getId(), employee.getId()), null);
        when(restaurantEmployeeEntityMapper.toEntity(restaurantEmployee))
                .thenReturn(restaurantEmployeeEntity);
        assertDoesNotThrow(
                ()-> restaurantPersistencePort.registerAnEmployee(restaurantEmployee)
        );
    }

    @Test
    void employeeWorkPlaceCorrectly() {
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        User employee = RestaurantData.EMPLOYEE;
        RestaurantEmployee restaurantEmployee = new RestaurantEmployee( restaurant.getId(), employee.getId() );
        RestaurantEmployeeEntity restaurantEmployeeEntity = new RestaurantEmployeeEntity(
                new RestaurantEmployeeEntityID(restaurant.getId(), employee.getId()), null);
        when(restaurantEmployeeRepository.findByIdIdUser(employee.getId()))
                .thenReturn(Optional.of(restaurantEmployeeEntity));
        when(restaurantEmployeeEntityMapper.toRestaurantEmployee(restaurantEmployeeEntity))
                .thenReturn(restaurantEmployee);
        assertEquals(
                restaurantEmployee,
                restaurantPersistencePort.employeeWorkPlace(employee)
        );
    }

    @Test
    void employeeWorkPlaceButTheEmployeeIsNotRegisteredInRestaurant() {
        Restaurant restaurant = RestaurantData.RESTAURANT_001;
        User employee = RestaurantData.EMPLOYEE;
        when(restaurantEmployeeRepository.findByIdIdUser(employee.getId()))
                .thenReturn(Optional.empty());
        assertThrows(
                WorkplaceNotFoundException.class,
                ()->restaurantPersistencePort.employeeWorkPlace(employee)
        );
    }
}