package com.ashish.QuickDish.service;

import com.ashish.QuickDish.Entity.CreateRestaurantRequest;
import com.ashish.QuickDish.Entity.Restaurant;
import com.ashish.QuickDish.Entity.User;
import com.ashish.QuickDish.dto.RestaurantDto;

import java.util.List;

public interface RestaurantService {

    Restaurant createRestaurant(CreateRestaurantRequest request, User user);
    Restaurant updateRestaurant(Long restaurantId,CreateRestaurantRequest updateRestaurant);
    void deleteRestaurant(Long restaurantId);
   List<Restaurant> getAllRestaurants();

    public List<Restaurant> searchRestaurant();
    Restaurant findRestaurantById(Long id);
    public Restaurant getRestaurantByUserId(Long userid);
    RestaurantDto addToFavorities(Long restaurantId, User user);
    Restaurant updateRestaurantStatus(Long restaurantId);

}
