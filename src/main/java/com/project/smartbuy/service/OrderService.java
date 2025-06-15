package com.project.smartbuy.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.project.smartbuy.model.Order;
import com.project.smartbuy.repository.OrderRepository;
import com.project.smartbuy.model.OrderItem;
import com.project.smartbuy.repository.OrderItemRepository;
import com.project.smartbuy.model.User;
import com.project.smartbuy.repository.UserRepository;
import com.project.smartbuy.model.Product;
import com.project.smartbuy.repository.ProductRepository;
import com.project.smartbuy.model.CartItem;
import com.project.smartbuy.repository.CartItemRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private UserRepository userRepository;
    private ProductRepository productRepository;
    private CartItemRepository cartItemRepository;

    public OrderService(OrderRepository orderRepository
    , OrderItemRepository orderItemRepository
    , UserRepository userRepository
    , ProductRepository productRepository
    , CartItemRepository cartItemRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    @Transactional
    public Order placeOrder(Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus("pending");

        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setPrice(cartItem.getProduct().getPrice());
            totalAmount = totalAmount.add(orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity())));
            orderItems.add(orderItem);

            // Reduce product stock
            Product product = cartItem.getProduct();
            product.setStock(product.getStock() - cartItem.getQuantity());
            productRepository.save(product);
        }
        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);
        // Save order and order items
        orderRepository.save(order);

        // Clear the cart
        cartItemRepository.deleteAll(cartItems);

        return order;

    }
    //Pay Order
    public void payOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        if (!order.getStatus().equals("pending")) {
            throw new IllegalStateException("Only pending orders can be paid.");
        }
        order.setStatus("paid");
        orderRepository.save(order);
    }
    //Merchant Shipping（Ship Order）——status: shipped
    public void shipOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        if (!order.getStatus().equals("paid")) {
            throw new IllegalStateException("Only paid orders can be shipped.");
        }
        order.setStatus("shipped");
        orderRepository.save(order);
    }
    //User confirms receipt of product（Complete Order）——Status: completed
    public void completeOrder(Long orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        if (!order.getStatus().equals("shipped")) {
            throw new IllegalStateException("Only shipped orders can be completed.");
        }
        order.setStatus("completed");
        orderRepository.save(order);
    }

    // View user's orders
    public List<Order> getOrdersByUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();
        return orderRepository.findByUser(user);
    }

}
