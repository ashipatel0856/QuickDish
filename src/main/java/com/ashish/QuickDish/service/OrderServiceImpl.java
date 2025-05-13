package com.ashish.QuickDish.service;

import com.ashish.QuickDish.Entity.Order;
import com.ashish.QuickDish.Entity.Restaurant;
import com.ashish.QuickDish.Entity.User;
import com.ashish.QuickDish.Entity.enums.OrderStatus;
import com.ashish.QuickDish.dto.OrderRequestDto;
import com.ashish.QuickDish.dto.OrderResponseDto;
import com.ashish.QuickDish.exceptions.ResourceNotFoundException;
import com.ashish.QuickDish.repository.OrderRepository;
import com.ashish.QuickDish.repository.RestaurantRepository;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.logging.Logger;

import static com.ashish.QuickDish.utils.AppUtils.getCurrentUser;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final RestaurantRepository restaurantRepository;
    private final UserService userService;
    private static final Logger log = Logger.getLogger(OrderServiceImpl.class.getName());

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, RestaurantRepository restaurantRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public OrderResponseDto BookingMyOrders(OrderRequestDto orderRequestDto) {
        log.info("booking orders by users");

        User  user = getCurrentUser();
        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("restaurant arenot found"));
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setRestaurant(restaurant);
        order.setCustomer(user);
        order.setTotalPrice(orderRequestDto.getTotalPrice());
        order.setStatus(OrderStatus.PLACED);
        order.setDeliveryAddress(orderRequestDto.getDeliveryAddress());
        order.setNotes(orderRequestDto.getNotes());
        order.setPaid(false);
        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderResponseDto.class);
    }

    @Override
    public OrderResponseDto getMyAllOrders(OrderRequestDto orderRequestDto) {

        return null;
    }

    @Override
    public OrderResponseDto getMyOrdersById(Long id) {
        return null;
    }

    @Override
    public OrderResponseDto updateMyOrdersStatusById(Long orderId, OrderRequestDto orderRequestDto) {
        return null;
    }
}
