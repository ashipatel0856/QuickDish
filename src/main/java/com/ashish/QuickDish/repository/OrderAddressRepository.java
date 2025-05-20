package com.ashish.QuickDish.repository;

import com.ashish.QuickDish.Entity.OrderAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderAddressRepository extends JpaRepository<OrderAddress,Long> {
}
