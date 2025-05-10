package com.ashish.QuickDish.service;

import com.ashish.QuickDish.dto.FoodItemDto;

import java.util.List;

public interface FoodService {
    FoodItemDto addNweFoodItem(FoodItemDto foodItemDto);
    FoodItemDto getFoodItemById(Long foodId);
    List<FoodItemDto> getAllFoodItems();
    FoodItemDto updateFoodItemById(Long foodId,FoodItemDto foodItemDto);
    void deleteFoodItemById(Long foodId);
    List<FoodItemDto> searchFoodItemByName(String name);
}
