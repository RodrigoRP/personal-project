package com.rodrigoramos.personalproject.service.interfaces;

import com.rodrigoramos.personalproject.dto.RestaurantDTO;
import com.rodrigoramos.personalproject.model.Restaurant;

import java.util.List;

public interface RestaurantService {

    Restaurant save(Restaurant restaurant);

    Restaurant convertDtoToEntity(RestaurantDTO dto);

    Restaurant findById(Long id);

    List<Restaurant> findAll();
}
