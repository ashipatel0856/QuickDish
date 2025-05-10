package com.ashish.QuickDish.service;

import com.ashish.QuickDish.Entity.CartItem;
import com.ashish.QuickDish.Entity.FoodItem;
import com.ashish.QuickDish.dto.CartItemResponseDto;
import com.ashish.QuickDish.exceptions.ResourceNotFoundException;
import com.ashish.QuickDish.repository.CartItemRepository;
import com.ashish.QuickDish.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final FoodRepository foodRepository;

    private static final Logger log = Logger.getLogger(CartItemServiceImpl.class.getName());

    public CartItemServiceImpl(CartItemRepository cartItemRepository, FoodRepository foodRepository) {
        this.cartItemRepository = cartItemRepository;
        this.foodRepository = foodRepository;
    }

    @Override
    public CartItemResponseDto addToCart(CartItemResponseDto cartItemResponseDto) {
        log.info("add cartitem for food");

        FoodItem foodItem = foodRepository.findById(cartItemResponseDto.getFoodItemId())
                .orElseThrow(() -> new ResourceNotFoundException("food not found with food id"));

        CartItem cartItem = new CartItem();
        cartItem.setFoodItem(foodItem);
        cartItem.setQuantity(cartItemResponseDto.getQuantity());
//        cartItem.setUserId(cartItemResponseDto.getUserId()); // Uncomment if needed

        CartItem savedCartItem = cartItemRepository.save(cartItem);

        // Map manually or using helper
        CartItemResponseDto responseDto = new CartItemResponseDto();
        responseDto.setId(savedCartItem.getId());
        responseDto.setFoodItemId(foodItem.getId());
        responseDto.setFoodName(foodItem.getName());
        responseDto.setUnitPrice(foodItem.getPrice());
        responseDto.setQuantity(savedCartItem.getQuantity());
        responseDto.setTotalPrice(foodItem.getPrice() * savedCartItem.getQuantity());
        return responseDto;
    }

    @Override
    public CartItemResponseDto updateCartItemQuantity(Long cartItemId, int quantity) {
        return null;
    }

    @Override
    public List<CartItemResponseDto> getAllCartItemsByUser(Long userId) {
        return List.of();
    }

    @Override
    public void deleteCartItem(Long cartItemId) {

    }
}
