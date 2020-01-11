package com.rodrigoramos.personalproject.controller;

import com.rodrigoramos.personalproject.dto.RestaurantDTO;
import com.rodrigoramos.personalproject.dto.UserResponseDTO;
import com.rodrigoramos.personalproject.model.Restaurant;
import com.rodrigoramos.personalproject.model.User;
import com.rodrigoramos.personalproject.service.interfaces.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<Void> saveRestaurant(@RequestBody RestaurantDTO dto) {
        Restaurant rest = restaurantService.convertDtoToEntity(dto);
        rest = restaurantService.save(rest);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(rest.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long id) {
        Restaurant restaurant = restaurantService.findById(id);

        return ResponseEntity.ok().body(restaurant);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<Restaurant>> findAll() {
        List<Restaurant> restaurantList = restaurantService.findAll();
        return ResponseEntity.ok().body(restaurantList);
    }
}
