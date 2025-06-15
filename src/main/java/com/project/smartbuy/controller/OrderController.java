package com.project.smartbuy.controller;

import java.util.List;
import com.project.smartbuy.model.Order;

import com.project.smartbuy.service.OrderService;
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
}
