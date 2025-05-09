package com.ashish.QuickDish.service;

import com.ashish.QuickDish.Entity.Restaurant;
import com.ashish.QuickDish.dto.RestaurantDto;
import com.ashish.QuickDish.repository.RestaurantRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, ModelMapper modelMapper) {
        this.restaurantRepository = restaurantRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public RestaurantDto createRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = modelMapper.map(restaurantDto, Restaurant.class);
        restaurant.setApproved(false);
        restaurant = restaurantRepository.save(restaurant);
        return modelMapper.map(restaurant, RestaurantDto.class);

    }
}
