package com.ashish.QuickDish.service;

import com.ashish.QuickDish.Entity.CartItem;
import com.ashish.QuickDish.Entity.FoodItem;
import com.ashish.QuickDish.dto.CartItemResponseDto;
import com.ashish.QuickDish.exceptions.ResourceNotFoundException;
import com.ashish.QuickDish.repository.CartItemRepository;
import com.ashish.QuickDish.repository.FoodRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final FoodRepository foodRepository;
    private final ModelMapper modelMapper;

    private static final Logger log = Logger.getLogger(CartItemServiceImpl.class.getName());

    public CartItemServiceImpl(CartItemRepository cartItemRepository, FoodRepository foodRepository, ModelMapper modelMapper) {
        this.cartItemRepository = cartItemRepository;
        this.foodRepository = foodRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CartItemResponseDto addToCart(CartItemResponseDto cartItemResponseDto) {
        log.info("add cartitem for food");

        FoodItem foodItem = foodRepository.findById(cartItemResponseDto.getFoodItemId())
                .orElseThrow(() -> new ResourceNotFoundException("food not found with food id"));

        CartItem cartItem = new CartItem();
        cartItem.setFoodItem(foodItem);
        cartItem.setQuantity(cartItemResponseDto.getQuantity());
        cartItem.setUserId(cartItemResponseDto.getUserId());

        CartItem savedCartItem = cartItemRepository.save(cartItem);
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
        log.info("update the cartitem quantity");
        CartItem cartitem = cartItemRepository.findById(cartItemId).orElseThrow(
                ()->new ResourceNotFoundException("cartitem are not found with cartitem id"));
//        modelMapper.map(CartItemResponseDto.class, cartitem);
        cartitem.setQuantity(quantity);
        double unitPrice = cartitem.getFoodItem().getPrice();
        cartitem.setUnitPrice(unitPrice);
        cartitem.setTotalPrice(unitPrice * quantity);
        CartItem saved = cartItemRepository.save(cartitem);
        return modelMapper.map(saved, CartItemResponseDto.class);
    }

    @Override
    public List<CartItemResponseDto> getAllCartItemsByUser(Long userId) {
        log.info("get all cartitems by user");
        List<CartItem> cartItems  = cartItemRepository.findByUserId(userId);
        return cartItems.stream()
                .map(ele ->modelMapper.map(ele,CartItemResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCartItem(Long cartItemId) {
        log.info("delete cart items by cartItemId");
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(() ->
                new ResourceNotFoundException("cartitem are not found with cartitem id"));
        cartItemRepository.delete(cartItem);


    }
}
