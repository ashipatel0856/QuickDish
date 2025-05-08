package com.ashish.QuickDish.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comments;
    private int rating;
    private LocalDateTime date;

    @ManyToOne
    private User user;

    @ManyToOne
    private Restaurant restaurant;
}
