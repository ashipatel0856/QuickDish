package com.ashish.QuickDish.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String contact;
    private String location;
    private boolean approved;

    @OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
    private List<FoodItem> foodItemList;

    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders;

    @OneToMany(mappedBy = "restaurant")
    private List<Review> reviews;
}
