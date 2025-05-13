package com.ashish.QuickDish.service;

import com.ashish.QuickDish.dto.OrderRequestDto;
import com.ashish.QuickDish.dto.OrderResponseDto;

public interface OrderService {
        OrderResponseDto BookingMyOrders(OrderRequestDto orderRequestDto);
        OrderResponseDto getMyAllOrders(OrderRequestDto orderRequestDto);
        OrderResponseDto getMyOrdersById(Long id);
        OrderResponseDto updateMyOrdersStatusById( Long orderId,OrderRequestDto orderRequestDto);

}
