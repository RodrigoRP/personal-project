package com.rodrigoramos.personalproject.controller;

import com.rodrigoramos.personalproject.model.Restaurant;
import com.rodrigoramos.personalproject.model.Vote;
import com.rodrigoramos.personalproject.service.interfaces.RestaurantService;
import com.rodrigoramos.personalproject.service.interfaces.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;
    @Autowired
    private RestaurantService restaurantService;

    @PostMapping(value = "/{idRestaurant}")
    public ResponseEntity<Void> saveVote(@PathVariable Long idRestaurant) {
        Restaurant restaurant = restaurantService.findById(idRestaurant);

        Vote vote = new Vote();
        vote = voteService.save(idRestaurant);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(vote.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
