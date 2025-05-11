package com.ashish.QuickDish.dto;

import com.ashish.QuickDish.Entity.Address;
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
    private List<Long> foodItemsIds;
    private Address address;

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<Long> getFoodItemsIds() {
        return foodItemsIds;
    }

    public void setFoodItemsIds(List<Long> foodItemsIds) {
        this.foodItemsIds = foodItemsIds;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
