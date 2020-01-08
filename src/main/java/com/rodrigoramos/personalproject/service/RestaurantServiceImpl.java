package com.rodrigoramos.personalproject.service;

import com.rodrigoramos.personalproject.dto.RestaurantDTO;
import com.rodrigoramos.personalproject.model.Restaurant;
import com.rodrigoramos.personalproject.repository.RestaurantRepository;
import com.rodrigoramos.personalproject.service.interfaces.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant convertDtoToEntity(RestaurantDTO dto) {
        return new Restaurant(null, dto.getNameRestaurant());
    }
}
