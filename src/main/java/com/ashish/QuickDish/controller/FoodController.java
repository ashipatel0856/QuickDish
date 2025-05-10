package com.ashish.QuickDish.controller;

import com.ashish.QuickDish.dto.FoodItemDto;
import com.ashish.QuickDish.service.FoodService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/foods/restaurants")
public class FoodController {

    private final FoodService foodService;
    private final ModelMapper modelMapper;

    public FoodController(FoodService foodService, ModelMapper modelMapper) {
        this.foodService = foodService;
        this.modelMapper = modelMapper;
    }
    @PostMapping
    public ResponseEntity<FoodItemDto> addNewFoodItem(@RequestBody FoodItemDto foodItemDto) {
        FoodItemDto foodItemDto1 = foodService.addNweFoodItem(foodItemDto);
        return new ResponseEntity<>(foodItemDto1, HttpStatus.CREATED);
    }

    @GetMapping("/{foodId}")
    public ResponseEntity<FoodItemDto> getFoodItemById(@PathVariable Long foodId) {
        FoodItemDto foodItemDto = foodService.getFoodItemById(foodId);
        return new ResponseEntity<>(foodItemDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FoodItemDto>> getAllFoodItems() {
        List<FoodItemDto> foodItemDtoList = foodService.getAllFoodItems();
        return new ResponseEntity<>(foodItemDtoList, HttpStatus.OK);
    }
    @PutMapping("/{foodId}")
    public ResponseEntity<FoodItemDto> updateFoodItemById(@PathVariable Long foodId, @RequestBody FoodItemDto foodItemDto) {
        FoodItemDto foodItemDto1 = foodService.updateFoodItemById(foodId, foodItemDto);
        return new ResponseEntity<>(foodItemDto1, HttpStatus.OK);
    }
    @DeleteMapping("/{foodId}")
    public ResponseEntity<FoodItemDto> deleteFoodItemById(@PathVariable Long foodId) {
        foodService.deleteFoodItemById(foodId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FoodItemDto>> searchFoodItemByName(@RequestParam  String name) {
        List<FoodItemDto> foodItemDtoList = foodService.searchFoodItemByName(name);
        return new ResponseEntity<>(foodItemDtoList, HttpStatus.OK);
    }
}
