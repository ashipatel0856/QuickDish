package com.ashish.QuickDish.repository;

import com.ashish.QuickDish.Entity.FoodItem;
import com.ashish.QuickDish.dto.FoodItemDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface FoodRepository extends JpaRepository<FoodItem ,Long> {

    List<FoodItem> findByNameContainingIgnoreCase(String name);
}
