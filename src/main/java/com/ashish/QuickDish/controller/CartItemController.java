package com.ashish.QuickDish.controller;

import com.ashish.QuickDish.dto.CartItemResponseDto;
import com.ashish.QuickDish.service.CartItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{cartItemId}")
    public ResponseEntity<CartItemResponseDto> updateCartItemQuantity(@PathVariable Long cartItemId, @RequestParam int quantity) {
        return ResponseEntity.ok(cartItemService.updateCartItemQuantity(cartItemId, quantity));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItemResponseDto>> getAllCartItemsByUserId(@PathVariable Long userId) {
        List<CartItemResponseDto> cartItemResponseDtos = cartItemService.getAllCartItemsByUser(userId);
        return ResponseEntity.ok(cartItemResponseDtos);

    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<CartItemResponseDto> deleteCartItem(@PathVariable Long cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
        return ResponseEntity.noContent().build();
    }
}
