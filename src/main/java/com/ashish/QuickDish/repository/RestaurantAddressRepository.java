package com.ashish.QuickDish.repository;

import com.ashish.QuickDish.Entity.RestaurantAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantAddressRepository extends JpaRepository<RestaurantAddress,Long> {
}
