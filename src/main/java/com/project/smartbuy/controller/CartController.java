package com.project.smartbuy.controller;

import com.project.smartbuy.model.CartItem;
import com.project.smartbuy.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    //Add to cart

    @PostMapping("/add")
    public CartItem addToCart(@RequestParam Long userId, @RequestParam Long productId, @RequestParam int quantity) {
        return cartService.addToCart(userId,productId,quantity);
    }

    // Remove from cart
    @DeleteMapping("/remove")
    public String removeFromCart(@RequestParam Long userId, @RequestParam Long productId) {
        cartService.removeFromCart(userId,productId);
        return "Removed From Cart";
    }

    //View user's cart
    @GetMapping("/view")
    public List<CartItem> viewCart(@RequestParam Long userId) {
        return cartService.getCartItems(userId);
    }
}
