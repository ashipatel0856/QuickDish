package com.ashish.QuickDish.repository;

import com.ashish.QuickDish.Entity.Order;
import com.ashish.QuickDish.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomer(User user);
   Optional<Order> findByPaymentSessionId(String sessionId);
}
