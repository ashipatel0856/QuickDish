package com.ashish.QuickDish.service;

import com.ashish.QuickDish.Entity.*;
import com.ashish.QuickDish.Entity.enums.OrderStatus;
import com.ashish.QuickDish.dto.AddOrderDto;
import com.ashish.QuickDish.dto.OrderItemRequestDto;
import com.ashish.QuickDish.dto.OrderRequestDto;
import com.ashish.QuickDish.dto.OrderResponseDto;
import com.ashish.QuickDish.exceptions.ResourceNotFoundException;
import com.ashish.QuickDish.exceptions.UnAuthorisedException;
import com.ashish.QuickDish.repository.FoodRepository;
import com.ashish.QuickDish.repository.OrderItemRepository;
import com.ashish.QuickDish.repository.OrderRepository;
import com.ashish.QuickDish.repository.RestaurantRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.ashish.QuickDish.utils.AppUtils.getCurrentUser;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final RestaurantRepository restaurantRepository;
    private final UserService userService;

    private final FoodRepository foodRepository;
    private final OrderItemRepository orderItemRepository;

    private static final Logger log = Logger.getLogger(OrderServiceImpl.class.getName());

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, RestaurantRepository restaurantRepository, UserService userService, FoodRepository foodRepository, OrderItemRepository orderItemRepository) {
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.foodRepository = foodRepository;
        this.orderItemRepository = orderItemRepository;
    }
    @Override
    @Transactional
    public OrderResponseDto BookingMyOrders(OrderRequestDto orderRequestDto) {
        log.info("booking orders by users");

        User user = getCurrentUser();
        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId())
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

        //  creating orders
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

        // create orders attactment to order-items
        List<OrderItemRequestDto> itemDtos = orderRequestDto.getOrderItems();
        if (itemDtos != null && !itemDtos.isEmpty()) {
            List<OrderItem> orderItems = new ArrayList<>();

            for (OrderItemRequestDto itemDto : itemDtos) {
                FoodItem foodItem = foodRepository.findById(itemDto.getFoodItemId())
                        .orElseThrow(() -> new ResourceNotFoundException("Food item not found"));

                OrderItem orderItem = new OrderItem();
                orderItem.setFoodItem(foodItem);
                orderItem.setQuantity(itemDto.getQuantity());
                orderItem.setPrice(foodItem.getPrice() * itemDto.getQuantity());
                orderItem.setOrder(savedOrder); // 💡 crucial line

                orderItems.add(orderItem);
            }
            savedOrder.setOrderItems(orderItems);
            orderRepository.save(savedOrder);
        }

        return modelMapper.map(savedOrder, OrderResponseDto.class);
    }



    @Override
    public List<OrderResponseDto> getMyAllOrders() {
        log.info("getting all bookings of orders");
        User user = getCurrentUser();
        List<Order> orderList = orderRepository.findAllByCustomer(user);
        return  orderList
                .stream()
                .map(orders -> modelMapper.map(orders,OrderResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto getMyOrdersById(Long id) {
        log.info("getting all bookings of orders by id");
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("orders are not found with id"));
        User user = getCurrentUser();
        if(!order.getCustomer().getId().equals(user.getId())) { // && isadmin(user)
            throw new UnAuthorisedException("you are not allowed access these orders ");
        }
        return modelMapper.map(order,OrderResponseDto.class);
    }

    @Override
    public OrderResponseDto updateMyOrdersStatusById(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new ResourceNotFoundException("orders are not found with order id"));

        User user = getCurrentUser();
//        if (!isAdmin(user)) {
//            throw new UnAuthorisedException("Only admins can update order status");
//        }


        if(!order.getCustomer().getId().equals(user.getId())) {
            throw new UnAuthorisedException("Only users can update this order   ");
        }
        order.setStatus(status);
        return modelMapper.map(orderRepository.save(order),OrderResponseDto.class);
    }





    @Override
    public OrderResponseDto addMyOrders(AddOrderDto addOrderDto) {
        log.info("adding  my extra orders");

        User user = getCurrentUser();
        Order order = orderRepository.findById(addOrderDto.getOrderId()).orElseThrow(
                () -> new ResourceNotFoundException("orders are not found with order id"));

        if (!order.getCustomer().getId().equals(user.getId())) {
            throw new UnAuthorisedException("Only users can add the order not unAuthorised person");
        }

        for (OrderItemRequestDto itemDto : addOrderDto.getItems()) {
       FoodItem foodItem = foodRepository.findById(itemDto.getFoodItemId())
                    .orElseThrow(() -> new ResourceNotFoundException("food items are not found with id"));

            OrderItem newOderItem = new OrderItem();
            newOderItem.setOrder(order);
            newOderItem.setFoodItem(foodItem);
            newOderItem.setQuantity(itemDto.getQuantity());

            order.getOrderItems().add(newOderItem);
        }
        // again recalculate total price
        double totalPrice = order.getOrderItems()
                .stream()
                .mapToDouble(amount -> amount.getFoodItem().getPrice() * amount.getQuantity())
                .sum();
        order.setTotalPrice(totalPrice);

        Order updatedOrder = orderRepository.save(order);
        return modelMapper.map(updatedOrder,OrderResponseDto.class);
    }
}
