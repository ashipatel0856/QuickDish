package com.ashish.QuickDish.service;

import com.ashish.QuickDish.Entity.CreateRestaurantRequest;
import com.ashish.QuickDish.Entity.Restaurant;
import com.ashish.QuickDish.Entity.User;
import com.ashish.QuickDish.dto.RestaurantDto;
import com.ashish.QuickDish.repository.AddressRepository;
import com.ashish.QuickDish.repository.RestaurnatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final AddressRepository addressRepository;
    private final RestaurnatRepository restaurnatRepository;
    private final UserService userService;

    public RestaurantServiceImpl(AddressRepository addressRepository, RestaurnatRepository restaurnatRepository, UserService userService) {
        this.addressRepository = addressRepository;
        this.restaurnatRepository = restaurnatRepository;

        this.userService = userService;
    }

    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest request, User user) {
        return null;
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updateRestaurant) {
        return null;
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {

    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return List.of();
    }

    @Override
    public List<Restaurant> searchRestaurant() {
        return List.of();
    }

    @Override
    public Restaurant findRestaurantById(Long id) {
        return null;
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userid) {
        return null;
    }

    @Override
    public RestaurantDto addToFavorities(Long restaurantId, User user) {
        return null;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long restaurantId) {
        return null;
    }
}
