package com.ashish.QuickDish.repository;

import com.ashish.QuickDish.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {

}
