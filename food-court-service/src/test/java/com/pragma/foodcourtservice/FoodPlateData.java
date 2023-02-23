package com.pragma.foodcourtservice;

import com.pragma.foodcourtservice.domain.model.FoodPlate;

public class FoodPlateData {
    public static FoodPlate FOOD_PLATE_001 = new FoodPlate(1l, "Plato maestro",
            CategoryData.CATEGORY_001.getId(),CategoryData.CATEGORY_001, "Descripcion del mejor plato", 100000l,
            RestaurantData.RESTAURANT_001.getId(), "url rara", true);
    public static FoodPlate VALID_FOOD_PLATE = new FoodPlate(1l, "Plato maestro",
            CategoryData.CATEGORY_001.getId(), CategoryData.CATEGORY_001, "Descripcion del mejor plato", 100000l,
            RestaurantData.RESTAURANT_001.getId(), "url rara", true);
    public static FoodPlate INVALID_PRICE_FOOD_PLATE = new FoodPlate(1l, "Plato maestro",
            CategoryData.CATEGORY_001.getId(), CategoryData.CATEGORY_001, "Descripcion del mejor plato", -100000l,
            RestaurantData.RESTAURANT_001.getId(), "url rara", true);
    public static FoodPlate INVALID_RESTAURANT_FOOD_PLATE = new FoodPlate(1l, "Plato maestro",
            CategoryData.CATEGORY_001.getId(), CategoryData.CATEGORY_001,"Descripcion del mejor plato", 100000l,
            RestaurantData.NON_INSERTED_RESTAURANT.getId(),"url rara", true);
    public static FoodPlate INVALID_CATEGORY_FOOD_PLATE = new FoodPlate(1l, "Plato maestro",
            CategoryData.NON_INSERTED_CATEGORY.getId(), CategoryData.NON_INSERTED_CATEGORY, "Descripcion del mejor plato", 100000l,
            RestaurantData.RESTAURANT_001.getId(),"url rara", true);
    public static FoodPlate clone(FoodPlate foodPlate){
        return new FoodPlate(foodPlate.getId(), foodPlate.getName(), foodPlate.getIdCategory(), foodPlate.getCategory(),
                foodPlate.getDescription(), foodPlate.getPrice(), foodPlate.getIdRestaurant(), foodPlate.getUrlImage(),
                foodPlate.getActive());
    }
}
