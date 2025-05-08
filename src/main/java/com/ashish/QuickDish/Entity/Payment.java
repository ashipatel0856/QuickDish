package com.ashish.QuickDish.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String paymentId;
    private boolean success;

    @OneToOne
    private Order order;

    private LocalDateTime paymentDate;
}
