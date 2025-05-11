package com.ashish.QuickDish.repository;

import com.ashish.QuickDish.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
