package com.project.smartbuy.repository;

import com.project.smartbuy.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.smartbuy.model.Order;
import java.util.*;
public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{
    List<OrderItem> findByOrder(Order order);
}
