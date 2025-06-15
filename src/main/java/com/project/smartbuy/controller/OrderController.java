package com.project.smartbuy.controller;

import java.util.List;
import com.project.smartbuy.model.Order;

import com.project.smartbuy.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    //place an order
    @PostMapping("/place")
    public Order placeOrder(@RequestBody Long userId){
        return orderService.placeOrder(userId);
    }

    //List all orders for a user
    @GetMapping("/list")
    public List<Order> getOrders(@RequestParam Long userId){
        return orderService.getOrdersByUser(userId);
    }

    //Payment order interface (simulated payment/Pay Order)
    @PostMapping("/pay")
    public ResponseEntity<String> payOrder(@RequestParam Long orderId) {
        orderService.payOrder(orderId);
        return ResponseEntity.ok("Payment successful, order is now paid.");
    }
    // shipping interface（Ship Order, for admin）
    @PostMapping("/ship")
    public ResponseEntity<String> shipOrder(@RequestParam Long orderId) {
        orderService.shipOrder(orderId);
        return ResponseEntity.ok("Order shipped.");
    }

    // User confirms receipt of products（Complete Order）
    @PostMapping("/complete")
    public ResponseEntity<String> completeOrder(@RequestParam Long orderId) {
        orderService.completeOrder(orderId);
        return ResponseEntity.ok("Order completed.");
    }
}


