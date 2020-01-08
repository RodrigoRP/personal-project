package com.rodrigoramos.personalproject.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String restaurantName;

    public Restaurant() {
    }

    public Restaurant(Long id, String restaurantName) {
        this.id = id;
        this.restaurantName = restaurantName;
    }


}
