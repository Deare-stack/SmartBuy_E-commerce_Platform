package com.project.smartbuy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.smartbuy.model.Order;
import com.project.smartbuy.model.User;
import com.project.smartbuy.repository.OrderRepository;
import com.project.smartbuy.repository.UserRepository;
import java.util.*;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
