package com.ashish.QuickDish.Entity;

import com.ashish.QuickDish.Entity.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderDate;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    @JsonIgnore
    private User customer;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private double totalPrice;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @OneToOne(mappedBy = "order",cascade = CascadeType.ALL)
    private Payment payment;
}
