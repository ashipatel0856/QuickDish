package com.ashish.QuickDish.service;

import com.ashish.QuickDish.dto.CartItemResponseDto;

import java.util.List;

public interface CartItemService {

    CartItemResponseDto addToCart(CartItemResponseDto cartItemResponseDto);
    CartItemResponseDto updateCartItemQuantity(Long cartItemId, int quantity);
    List<CartItemResponseDto> getAllCartItemsByUser(Long userId);
    void deleteCartItem(Long cartItemId);
}
