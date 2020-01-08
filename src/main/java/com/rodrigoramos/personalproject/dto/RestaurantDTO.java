package com.rodrigoramos.personalproject.dto;

import lombok.Data;

@Data
public class RestaurantDTO {

    private String nameRestaurant;

    public RestaurantDTO() {
    }

    public RestaurantDTO(String nameRestaurant) {
        this.nameRestaurant = nameRestaurant;
    }
}
