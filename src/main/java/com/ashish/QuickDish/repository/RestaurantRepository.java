package com.ashish.QuickDish.repository;

import com.ashish.QuickDish.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


public interface RestaurantRepository extends JpaRepository< Restaurant,Long> {

}
