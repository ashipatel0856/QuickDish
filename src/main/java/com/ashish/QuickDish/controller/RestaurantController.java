package com.ashish.QuickDish.controller;


import com.ashish.QuickDish.dto.RestaurantDto;
import com.ashish.QuickDish.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/Create")
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody RestaurantDto restaurantDto) {
        return new ResponseEntity<>(restaurantService.createRestaurant(restaurantDto), HttpStatus.CREATED);

    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<RestaurantDto> getRestaurant(@PathVariable Long restaurantId) {
        RestaurantDto restaurantDto = restaurantService.getRestaurantById(restaurantId);
        return new ResponseEntity<>(restaurantDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants(){
        List<RestaurantDto> restaurantDtos = restaurantService.getAllRestaurants();
        return new ResponseEntity<>(restaurantDtos,HttpStatus.OK);

    }

    @PutMapping("/{restId}")
    public ResponseEntity<RestaurantDto> updateRestaurantById(@PathVariable Long restId,@RequestBody RestaurantDto restaurantDto ){
        RestaurantDto restaurantDto1 = restaurantService.updateRestaurantById(restId,restaurantDto);
        return new ResponseEntity<>(restaurantDto1,HttpStatus.OK);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<?> deleteRestaurantById(@PathVariable Long restaurantId){
       restaurantService.deleteRestaurantById(restaurantId);
        return ResponseEntity.noContent().build();
    }
}
