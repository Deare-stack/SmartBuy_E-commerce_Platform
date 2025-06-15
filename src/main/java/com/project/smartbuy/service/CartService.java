package com.project.smartbuy.service;

import com.project.smartbuy.model.CartItem;
import com.project.smartbuy.model.User;
import com.project.smartbuy.model.Product;
import com.project.smartbuy.repository.CartItemRepository;
import com.project.smartbuy.repository.UserRepository;
import com.project.smartbuy.repository.ProductRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartItemRepository cartItemRepository,
                       ProductRepository productRepository,
                       UserRepository userRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }
    // Add item to cart (if exists, increase quantity)
    public CartItem addToCart(long userId, long productId, int quantity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItem> existing = cartItemRepository.findByUserAndProduct(user, product);
        CartItem cartItem;
        if (existing.isPresent()) {
            cartItem = existing.get();
            cartItem.setQuantity(cartItem.getQuantity()+quantity);
        }else{
            cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
        }
        return cartItemRepository.save(cartItem);
    }

    //remove item from cart
    public void removeFromCart(long userId, long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        cartItemRepository.deleteByUserAndProduct(user, product);
    }

    //view all items in  user's cart
    public List<CartItem> getCartItems(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return cartItemRepository.findByUser(user);
    }
}
