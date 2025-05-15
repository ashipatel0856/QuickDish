package com.ashish.QuickDish.service;

import com.ashish.QuickDish.Entity.Order;
import com.ashish.QuickDish.Entity.enums.OrderStatus;
import com.ashish.QuickDish.dto.AddOrderDto;
import com.ashish.QuickDish.dto.OrderRequestDto;
import com.ashish.QuickDish.dto.OrderResponseDto;

import java.util.List;

public interface OrderService {
        OrderResponseDto BookingMyOrders(OrderRequestDto orderRequestDto);
        List<OrderResponseDto> getMyAllOrders();
        OrderResponseDto getMyOrdersById(Long id);
        OrderResponseDto updateMyOrdersStatusById(Long orderId, OrderStatus status);
        OrderResponseDto addMyOrders(AddOrderDto addOrderDto);
       void markOrderAsPaid(String sessionId);
        Order getOrderById(Long orderId);

}
