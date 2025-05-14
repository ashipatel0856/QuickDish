package com.ashish.QuickDish.repository;

import com.ashish.QuickDish.Entity.Order;
import com.ashish.QuickDish.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomer(User user);
}
