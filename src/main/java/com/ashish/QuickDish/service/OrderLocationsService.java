package com.ashish.QuickDish.service;

import com.ashish.QuickDish.Entity.*;
import com.ashish.QuickDish.exceptions.ResourceNotFoundException;
import com.ashish.QuickDish.repository.*;
import org.springframework.stereotype.Service;

@Service
public class OrderLocationsService {

    // Required repos
    private final OrderAddressRepository orderAddressRepository;
    private final RestaurantAddressRepository restaurantAddressRepository;
    private final UserAddressRepository userAddressRepository;
    private final DeliveryRiderRepository deliveryRiderRepository;
    private final UserRepository userRepository;

    public OrderLocationsService(OrderAddressRepository orderAddressRepository,
                                 RestaurantAddressRepository restaurantAddressRepository,
                                 UserAddressRepository userAddressRepository,
                                 DeliveryRiderRepository deliveryRiderRepository,
                                 UserRepository userRepository) {
        this.orderAddressRepository = orderAddressRepository;
        this.restaurantAddressRepository = restaurantAddressRepository;
        this.userAddressRepository = userAddressRepository;
        this.deliveryRiderRepository = deliveryRiderRepository;
        this.userRepository = userRepository;
    }

    public OrderAdrress placeOrder(Long userId, Long restaurantId, Long addressId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new ResourceNotFoundException("UserId not found"));

        RestaurantAddress restaurantAddress = restaurantAddressRepository.findById(restaurantId).orElseThrow(() ->
                new ResourceNotFoundException("RestaurantId not found"));

        UserAddress userAddress = userAddressRepository.findById(addressId).orElseThrow(() ->
                new ResourceNotFoundException("AddressId not found"));

        // ✅ Calculate distance
        double distanceInKm = CheckNearestDistance.distanceInKm(
                restaurantAddress.getLatitude(),
                restaurantAddress.getLongitude(),
                userAddress.getLatitude(),
                userAddress.getLongitude()
        );

        // ✅ Set delivery charges
        double charges = distanceInKm <= 5 ? 20 : 40;

        // ✅ Find nearest delivery rider (just a mock logic)
        DeliveryRider nearestRider = findNearestRider(restaurantAddress.getLatitude(), restaurantAddress.getLongitude());

        OrderAdrress orderAdrress = new OrderAdrress();
        orderAdrress.setUser(user);
        orderAdrress.setRestaurant(restaurantAddress); // Only if you update the field to RestaurantAddress
        orderAdrress.setUserAddress(userAddress);
        orderAdrress.setRider(nearestRider); // ✅ FIXED method name
        orderAdrress.setDeliveryCharge(charges);
        orderAdrress.setDeliveryDistance(String.valueOf(distanceInKm)); // ✅ FIXED


        return orderAddressRepository.save(orderAdrress);
    }

    private DeliveryRider findNearestRider(double lat, double lon) {
        // For now, just return first rider (you can apply logic using distance calculation)
        return deliveryRiderRepository.findAll().stream().findFirst().orElseThrow(() ->
                new ResourceNotFoundException("No delivery rider found"));
    }
}
