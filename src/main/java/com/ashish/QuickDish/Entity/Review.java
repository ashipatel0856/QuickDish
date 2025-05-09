package com.ashish.QuickDish.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private User user;

    @ManyToOne
    private Restaurant restaurant;
}
