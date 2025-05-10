package com.ashish.QuickDish.controller;

import com.ashish.QuickDish.dto.CartItemResponseDto;
import com.ashish.QuickDish.service.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carts")
public class CartItemController {
    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping
    public ResponseEntity<CartItemResponseDto> addToCart(@RequestBody CartItemResponseDto cartItemResponseDto) {
       CartItemResponseDto cartItemResponseDto1 = cartItemService.addToCart(cartItemResponseDto);
       return ResponseEntity.ok(cartItemResponseDto1);
    }
}
