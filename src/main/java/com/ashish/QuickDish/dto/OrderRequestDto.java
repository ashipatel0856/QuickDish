package com.ashish.QuickDish.dto;

import com.ashish.QuickDish.Entity.Address;
import com.ashish.QuickDish.Entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDto {

 private Long restaurantId;
 private double totalPrice;
 private String deliveryAddress;
 private String notes;
 private OrderStatus status;
 private List<OrderItemRequestDto> orderItems;

 public List<OrderItemRequestDto> getOrderItems() {
  return orderItems;
 }

 public void setOrderItems(List<OrderItemRequestDto> orderItems) {
  this.orderItems = orderItems;
 }

 public OrderStatus getStatus() {
  return status;
 }

 public void setStatus(OrderStatus status) {
  this.status = status;
 }

 public Long getRestaurantId() {
  return restaurantId;
 }

 public void setRestaurantId(Long restaurantId) {
  this.restaurantId = restaurantId;
 }

 public double getTotalPrice() {
  return totalPrice;
 }

 public void setTotalPrice(double totalPrice) {
  this.totalPrice = totalPrice;
 }

 public String getDeliveryAddress() {
  return deliveryAddress;
 }

 public void setDeliveryAddress(String deliveryAddress) {
  this.deliveryAddress = deliveryAddress;
 }

 public String getNotes() {
  return notes;
 }

 public void setNotes(String notes) {
  this.notes = notes;
 }
}
