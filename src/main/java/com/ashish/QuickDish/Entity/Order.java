package com.ashish.QuickDish.Entity;

import com.ashish.QuickDish.Entity.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime orderDate;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    @JsonBackReference
    private User customer;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private double totalPrice;

//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private Order order;


    @OneToOne(mappedBy = "order",cascade = CascadeType.ALL)
    private Payment payment;
}
