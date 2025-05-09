package com.ashish.QuickDish.service;

import com.ashish.QuickDish.dto.RestaurantDto;

import java.util.List;

public interface RestaurantService {
    RestaurantDto createRestaurant(RestaurantDto restaurantDto);
    List<RestaurantDto> getAllRestaurants();
    RestaurantDto getRestaurantById(Long id);
    RestaurantDto updateRestaurantById(Long restId,RestaurantDto restaurantDto);
    Void deleteRestaurantById(Long id);

}
