package com.pragma.foodcourtservice.domain.spi;

import com.pragma.foodcourtservice.CategoryData;
import com.pragma.foodcourtservice.FoodPlateData;
import com.pragma.foodcourtservice.RestaurantData;
import com.pragma.foodcourtservice.domain.model.FoodPlate;
import com.pragma.foodcourtservice.infrastructure.exception.FoodPlateNotFoundException;
import com.pragma.foodcourtservice.infrastructure.output.jpa.adapter.FoodPlateJpaAdapter;
import com.pragma.foodcourtservice.infrastructure.output.jpa.entity.FoodPlateEntity;
import com.pragma.foodcourtservice.infrastructure.output.jpa.mapper.FoodPlateEntityMapper;
import com.pragma.foodcourtservice.infrastructure.output.jpa.repository.IFoodPlateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class IFoodPlatePersistencePortTest {
    IFoodPlateRepository foodPlateRepository;
    FoodPlateEntityMapper foodPlateEntityMapper;

    IFoodPlatePersistencePort foodPlatePersistencePort;

    @BeforeEach
    void setUp(){
        foodPlateRepository = mock(IFoodPlateRepository.class);
        foodPlateEntityMapper = mock(FoodPlateEntityMapper.class);
        foodPlatePersistencePort = new FoodPlateJpaAdapter(foodPlateRepository, foodPlateEntityMapper);
    }

    @Test
    void saveFoodPlate() {
        FoodPlate foodPlate = FoodPlateData.FOOD_PLATE_001;

        FoodPlateEntity foodPlateEntity = new FoodPlateEntity(
                1L, foodPlate.getName(), foodPlate.getIdCategory(),null, foodPlate.getDescription(),
                foodPlate.getPrice(), foodPlate.getIdRestaurant(), null, foodPlate.getUrlImage(),
                foodPlate.getActive()
        );
        when(foodPlateRepository.findById(foodPlate.getId()))
                .thenReturn(Optional.of(foodPlateEntity));
        when(foodPlateEntityMapper.toFoodPlate(foodPlateEntity))
                .thenReturn(foodPlate);
        assertEquals(foodPlate, foodPlatePersistencePort.getFoodPlate(foodPlate.getId()));
    }

    @Test
    void updateFoodPlate() {
        FoodPlate foodPlate = FoodPlateData.FOOD_PLATE_001;

        FoodPlateEntity foodPlateEntity = new FoodPlateEntity(
                foodPlate.getId(), foodPlate.getName(), foodPlate.getIdCategory(),null, foodPlate.getDescription(),
                foodPlate.getPrice(), foodPlate.getIdRestaurant(), null, foodPlate.getUrlImage(),
                foodPlate.getActive()
        );
        when(foodPlateEntityMapper.toFoodPlate(foodPlateEntity))
                .thenReturn(foodPlate);
        assertEquals(foodPlate, foodPlatePersistencePort.updateFoodPlate(foodPlate));
    }

    @Test
    void getFoodPlateCorrectly() {
        FoodPlate foodPlate = FoodPlateData.FOOD_PLATE_001;

        FoodPlateEntity foodPlateEntity = new FoodPlateEntity(
                foodPlate.getId(), foodPlate.getName(), foodPlate.getIdCategory(),null, foodPlate.getDescription(),
                foodPlate.getPrice(), foodPlate.getIdRestaurant(), null, foodPlate.getUrlImage(),
                foodPlate.getActive()
        );
        when(foodPlateEntityMapper.toFoodPlate(foodPlateEntity))
                .thenReturn(foodPlate);
        when(foodPlateRepository.findById(foodPlate.getId()))
                .thenReturn(Optional.of(foodPlateEntity));
        assertEquals(foodPlate, foodPlatePersistencePort.getFoodPlate(foodPlate.getId()));
    }

    @Test
    void getFoodPlateButDoesNotExist() {
        FoodPlate foodPlate = FoodPlateData.FOOD_PLATE_001;

        FoodPlateEntity foodPlateEntity = new FoodPlateEntity(
                foodPlate.getId(), foodPlate.getName(), foodPlate.getIdCategory(),null, foodPlate.getDescription(),
                foodPlate.getPrice(), foodPlate.getIdRestaurant(), null, foodPlate.getUrlImage(),
                foodPlate.getActive()
        );
        when(foodPlateEntityMapper.toFoodPlate(foodPlateEntity))
                .thenReturn(foodPlate);
        when(foodPlateRepository.findById(foodPlate.getId()))
                .thenReturn(Optional.empty());
        assertThrows(FoodPlateNotFoundException.class,
                ()->foodPlatePersistencePort.getFoodPlate(foodPlate.getId()));
    }

    @Test
    void listTheFoodPlatesByCategoryProvidingCategories() {
        List<FoodPlate> foodPlates = new ArrayList<>(
                List.of(
                        new FoodPlate(1L,"Phalacrocorax varius",3L,null,"Schiller Group",7872l,1l,"http://dummyimage.com/114x100.png/5fa2dd/ffffff",true),
                        new FoodPlate(2L,"Larus fuliginosus",1L,null,"Altenwerth Group",7183l,2l,"http://dummyimage.com/122x100.png/dddddd/000000",false),
                        new FoodPlate(3L,"Marmota monax",3L,null,"Ebert-Gleason",9073l,1l,"http://dummyimage.com/240x100.png/dddddd/000000",false),
                        new FoodPlate(4L,"Corvus albus",2L,null,"Kassulke-Gutmann",7019l,2l,"http://dummyimage.com/101x100.png/cc0000/ffffff",false),
                        new FoodPlate(5L,"Lasiodora parahybana",1L,null,"Skiles, Monahan and Effertz",5766l,2l,"http://dummyimage.com/174x100.png/dddddd/000000",false),
                        new FoodPlate(6L,"Nucifraga columbiana",1L,null,"Monahan-Wilkinson",5896l,1l,"http://dummyimage.com/138x100.png/dddddd/000000",true),
                        new FoodPlate(7L,"Sula dactylatra",1L,null,"Trantow and Sons",4554l,1l,"http://dummyimage.com/172x100.png/ff4444/ffffff",false),
                        new FoodPlate(8L,"Ursus americanus",3L,null,"Kris, Armstrong and Nolan",1766l,1l,"http://dummyimage.com/225x100.png/5fa2dd/ffffff",false)
                )
        );
        List<FoodPlateEntity> foodPlatesEntity = new ArrayList<>(
                List.of(
                        new FoodPlateEntity(1L,"Phalacrocorax varius",3L,null,"Schiller Group",7872l,1l,null,"http://dummyimage.com/114x100.png/5fa2dd/ffffff",true),
                        new FoodPlateEntity(2L,"Larus fuliginosus",1L,null,"Altenwerth Group",7183l,2l,null,"http://dummyimage.com/122x100.png/dddddd/000000",false),
                        new FoodPlateEntity(3L,"Marmota monax",3L,null,"Ebert-Gleason",9073l,1l,null,"http://dummyimage.com/240x100.png/dddddd/000000",false),
                        new FoodPlateEntity(4L,"Corvus albus",2L,null,"Kassulke-Gutmann",7019l,2l,null,"http://dummyimage.com/101x100.png/cc0000/ffffff",false),
                        new FoodPlateEntity(5L,"Lasiodora parahybana",1L,null,"Skiles, Monahan and Effertz",5766l,2l,null,"http://dummyimage.com/174x100.png/dddddd/000000",false),
                        new FoodPlateEntity(6L,"Nucifraga columbiana",1L,null,"Monahan-Wilkinson",5896l,1l,null,"http://dummyimage.com/138x100.png/dddddd/000000",true),
                        new FoodPlateEntity(7L,"Sula dactylatra",1L,null,"Trantow and Sons",4554l,1l,null,"http://dummyimage.com/172x100.png/ff4444/ffffff",false),
                        new FoodPlateEntity(8L,"Ursus americanus",3L,null,"Kris, Armstrong and Nolan",1766l,1l,null,"http://dummyimage.com/225x100.png/5fa2dd/ffffff",false)
                )
        );
        Long idRestaurant = 1L;
        List<Long> categories = List.of(1L, 2L);
        foodPlates =foodPlates.stream().filter(
                foodPlate -> new TreeSet<>(categories).contains(foodPlate.getIdCategory())
        ).collect(Collectors.toList());
        foodPlatesEntity = foodPlatesEntity.stream().filter(
                foodPlate -> new TreeSet<>(categories).contains(foodPlate.getIdCategory())
        ).collect(Collectors.toList());
        int page = 1;
        int number = 10;
        for(int i = 0 ; i < page*number && !foodPlates.isEmpty() ; ++i){
            foodPlates.remove(0);
            foodPlatesEntity.remove(0);
        }
        while(foodPlates.size() > number){
            foodPlates.remove(number);
            foodPlatesEntity.remove(number);
        }
        for(int i = 0 ; i < foodPlates.size() ; ++i){
            FoodPlate foodPlate = foodPlates.get(i);
            FoodPlateEntity foodPlateEntity = foodPlatesEntity.get(i);
            Long idFoodPlate = foodPlates.get(i).getId();
            when(foodPlateRepository.findById(idFoodPlate))
                    .thenReturn(Optional.of(foodPlateEntity));
            when(foodPlateEntityMapper.toFoodPlate(foodPlateEntity))
                    .thenReturn(foodPlate);
        }
        when(foodPlateRepository.findAllGroupByCategoryAndPaginated(idRestaurant,categories, page*number, number))
                .thenReturn(foodPlatesEntity);
        when(foodPlatePersistencePort.listTheFoodPlatesByCategory(idRestaurant, categories, page, number))
                .thenReturn(foodPlates);
    }

    @Test
    void listTheFoodPlatesByCategoryWithoutCategories() {
        List<FoodPlate> foodPlates = new ArrayList<>(
                List.of(
                        new FoodPlate(1L,"Phalacrocorax varius",3L,null,"Schiller Group",7872l,1l,"http://dummyimage.com/114x100.png/5fa2dd/ffffff",true),
                        new FoodPlate(2L,"Larus fuliginosus",1L,null,"Altenwerth Group",7183l,2l,"http://dummyimage.com/122x100.png/dddddd/000000",false),
                        new FoodPlate(3L,"Marmota monax",3L,null,"Ebert-Gleason",9073l,1l,"http://dummyimage.com/240x100.png/dddddd/000000",false),
                        new FoodPlate(4L,"Corvus albus",2L,null,"Kassulke-Gutmann",7019l,2l,"http://dummyimage.com/101x100.png/cc0000/ffffff",false),
                        new FoodPlate(5L,"Lasiodora parahybana",1L,null,"Skiles, Monahan and Effertz",5766l,2l,"http://dummyimage.com/174x100.png/dddddd/000000",false),
                        new FoodPlate(6L,"Nucifraga columbiana",1L,null,"Monahan-Wilkinson",5896l,1l,"http://dummyimage.com/138x100.png/dddddd/000000",true),
                        new FoodPlate(7L,"Sula dactylatra",1L,null,"Trantow and Sons",4554l,1l,"http://dummyimage.com/172x100.png/ff4444/ffffff",false),
                        new FoodPlate(8L,"Ursus americanus",3L,null,"Kris, Armstrong and Nolan",1766l,1l,"http://dummyimage.com/225x100.png/5fa2dd/ffffff",false)
                )
        );
        List<FoodPlateEntity> foodPlatesEntity = new ArrayList<>(
                List.of(
                        new FoodPlateEntity(1L,"Phalacrocorax varius",3L,null,"Schiller Group",7872l,1l,null,"http://dummyimage.com/114x100.png/5fa2dd/ffffff",true),
                        new FoodPlateEntity(2L,"Larus fuliginosus",1L,null,"Altenwerth Group",7183l,2l,null,"http://dummyimage.com/122x100.png/dddddd/000000",false),
                        new FoodPlateEntity(3L,"Marmota monax",3L,null,"Ebert-Gleason",9073l,1l,null,"http://dummyimage.com/240x100.png/dddddd/000000",false),
                        new FoodPlateEntity(4L,"Corvus albus",2L,null,"Kassulke-Gutmann",7019l,2l,null,"http://dummyimage.com/101x100.png/cc0000/ffffff",false),
                        new FoodPlateEntity(5L,"Lasiodora parahybana",1L,null,"Skiles, Monahan and Effertz",5766l,2l,null,"http://dummyimage.com/174x100.png/dddddd/000000",false),
                        new FoodPlateEntity(6L,"Nucifraga columbiana",1L,null,"Monahan-Wilkinson",5896l,1l,null,"http://dummyimage.com/138x100.png/dddddd/000000",true),
                        new FoodPlateEntity(7L,"Sula dactylatra",1L,null,"Trantow and Sons",4554l,1l,null,"http://dummyimage.com/172x100.png/ff4444/ffffff",false),
                        new FoodPlateEntity(8L,"Ursus americanus",3L,null,"Kris, Armstrong and Nolan",1766l,1l,null,"http://dummyimage.com/225x100.png/5fa2dd/ffffff",false)
                )
        );
        Long idRestaurant = 1L;
        List<Long> categories = List.of();
        int page = 0;
        int number = 10;
        for(int i = 0 ; i < page*number && !foodPlates.isEmpty() ; ++i){
            foodPlates.remove(0);
            foodPlatesEntity.remove(0);
        }
        while(foodPlates.size() > number){
            foodPlates.remove(number);
            foodPlatesEntity.remove(number);
        }

        for(int i = 0 ; i < foodPlates.size() ; ++i){
            FoodPlate foodPlate = foodPlates.get(i);
            FoodPlateEntity foodPlateEntity = foodPlatesEntity.get(i);
            Long idFoodPlate = foodPlates.get(i).getId();
            when(foodPlateRepository.findById(idFoodPlate))
                    .thenReturn(Optional.of(foodPlateEntity));
            when(foodPlateEntityMapper.toFoodPlate(foodPlateEntity))
                    .thenReturn(foodPlate);
        }
        when(foodPlateRepository.findAllAndPaginated(idRestaurant, page*number, number))
                .thenReturn(foodPlatesEntity);
        assertEquals(
                foodPlatePersistencePort.listTheFoodPlatesByCategory(idRestaurant, categories, page, number),
                foodPlates
        );
    }
}