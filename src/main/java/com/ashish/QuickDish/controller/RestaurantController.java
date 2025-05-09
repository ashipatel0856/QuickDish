package com.ashish.QuickDish.controller;


import com.ashish.QuickDish.dto.RestaurantDto;
import com.ashish.QuickDish.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/Create")
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody RestaurantDto restaurantDto) {
        return new ResponseEntity<>(restaurantService.createRestaurant(restaurantDto), HttpStatus.CREATED);

    }
}
