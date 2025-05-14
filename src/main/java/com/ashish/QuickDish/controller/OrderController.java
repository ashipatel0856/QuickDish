package com.ashish.QuickDish.controller;

import com.ashish.QuickDish.Entity.enums.OrderStatus;
import com.ashish.QuickDish.dto.AddOrderDto;
import com.ashish.QuickDish.dto.OrderRequestDto;
import com.ashish.QuickDish.dto.OrderResponseDto;
import com.ashish.QuickDish.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders/customers")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDto> BookingMyOrders(@RequestBody OrderRequestDto orderRequestDto) {
        OrderResponseDto orderResponseDto = orderService.BookingMyOrders(orderRequestDto);
        return ResponseEntity.ok(orderResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getMyAllOrders() {
        List<OrderResponseDto> orderResponseDto = orderService.getMyAllOrders();
        return ResponseEntity.ok(orderResponseDto);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getMyOrdersById( @PathVariable Long orderId){
        OrderResponseDto orderResponseDto = orderService.getMyOrdersById(orderId);
        return ResponseEntity.ok(orderResponseDto);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> updateMyOrdersStatusById(@PathVariable Long orderId,
                                                                     @RequestBody OrderStatus status){
        OrderResponseDto orderResponseDto = orderService.updateMyOrdersStatusById(orderId,status);
        return ResponseEntity.ok(orderResponseDto);
    }


    @PostMapping("/add-items")
    public ResponseEntity<OrderResponseDto> addMyOrders(@RequestBody AddOrderDto addOrderDto){
        OrderResponseDto orderResponseDto = orderService.addMyOrders(addOrderDto);
        return ResponseEntity.ok(orderResponseDto);
    }
}
