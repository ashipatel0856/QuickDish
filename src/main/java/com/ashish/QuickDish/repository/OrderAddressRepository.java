package com.ashish.QuickDish.repository;

import com.ashish.QuickDish.Entity.OrderAdrress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderAddressRepository extends JpaRepository<OrderAdrress,Long> {
}
