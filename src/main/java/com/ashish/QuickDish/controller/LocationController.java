package com.ashish.QuickDish.controller;

import com.ashish.QuickDish.Entity.OrderAdrress;
import com.ashish.QuickDish.service.OrderLocationsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final OrderLocationsService orderLocationsService;

    public LocationController(OrderLocationsService orderLocationsService) {
        this.orderLocationsService = orderLocationsService;
    }
    @PostMapping("/place")
    public ResponseEntity<OrderAdrress> placeOrder(@RequestParam Long userId,
                                                   @RequestParam Long restaurantId,
                                                   @RequestParam Long addressId){

        OrderAdrress orderAdrress = orderLocationsService.placeOrder(userId, restaurantId, addressId);
        return ResponseEntity.ok(orderAdrress);
    }
}
