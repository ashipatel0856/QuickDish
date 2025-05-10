package com.ashish.QuickDish.service;

import com.ashish.QuickDish.Entity.Restaurant;
import com.ashish.QuickDish.dto.RestaurantDto;
import com.ashish.QuickDish.exceptions.ResourceNotFoundException;
import com.ashish.QuickDish.repository.RestaurantRepository;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;
    private static final Logger log = LoggerFactory.getLogger(RestaurantServiceImpl.class);

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

    @Override
    public List<RestaurantDto> getAllRestaurants() {
        log.info("getting all restaurant{}");
     List<Restaurant> restaurants = restaurantRepository.findAll();
     return restaurants
             .stream()
             .map(restaurant -> modelMapper.map(restaurant, RestaurantDto.class))
             .collect(Collectors.toList());
    }

    @Override
    public RestaurantDto getRestaurantById(Long id) {
        log.info("geting restaurant with id {}");
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Restaurant are  not found with restaurantID"));
        return modelMapper.map(restaurant, RestaurantDto.class);
    }

    @Override
    public RestaurantDto updateRestaurantById(Long restId,RestaurantDto restaurantDto) {
        log.info("update the restaurant by id");
        Restaurant restaurant = restaurantRepository.findById(restId).orElseThrow(() ->
                new ResourceNotFoundException("not found restaurant with id"));

        modelMapper.map(restaurantDto, restaurant);
        restaurant.setId(restId);
        restaurant = restaurantRepository.save(restaurant);
        return modelMapper.map(restaurant,RestaurantDto.class);

    }

    @Override
    public void deleteRestaurantById(Long id) {
        log.info("Deleting restaurant with id: {}", id);

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant does not exist with id: " + id));

        restaurantRepository.deleteById(id);
    }

}
