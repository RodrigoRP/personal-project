package com.rodrigoramos.personalproject.controller;

import com.rodrigoramos.personalproject.model.Restaurant;
import com.rodrigoramos.personalproject.model.Vote;
import com.rodrigoramos.personalproject.service.interfaces.RestaurantService;
import com.rodrigoramos.personalproject.service.interfaces.VoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/vote")
public class VoteController {

    private final VoteService voteService;
    private final RestaurantService restaurantService;

    public VoteController(VoteService voteService, RestaurantService restaurantService) {
        this.voteService = voteService;
        this.restaurantService = restaurantService;
    }

    @PostMapping(value = "/{idRestaurant}")
    public ResponseEntity<Void> saveVote(@PathVariable Long idRestaurant) {
        restaurantService.findById(idRestaurant);
        Vote vote = voteService.save(idRestaurant);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(vote.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/winner")
    public ResponseEntity<Restaurant> getWinner() {
        Restaurant winnerToday = voteService.winnerToday();
        return ResponseEntity.ok().body(winnerToday);
    }

    @GetMapping(value = "/restaurant/{idRestaurant}")
    public Long getVotesRestaurant(@PathVariable Long idRestaurant) {
        return voteService.countVotesByRestaurant(idRestaurant);
    }


}
