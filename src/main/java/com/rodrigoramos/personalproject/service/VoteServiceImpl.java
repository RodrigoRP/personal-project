package com.rodrigoramos.personalproject.service;

import com.rodrigoramos.personalproject.model.Restaurant;
import com.rodrigoramos.personalproject.model.User;
import com.rodrigoramos.personalproject.model.Vote;
import com.rodrigoramos.personalproject.repository.UserRepository;
import com.rodrigoramos.personalproject.repository.VoteRepository;
import com.rodrigoramos.personalproject.security.UserSS;
import com.rodrigoramos.personalproject.service.interfaces.RestaurantService;
import com.rodrigoramos.personalproject.service.interfaces.UserService;
import com.rodrigoramos.personalproject.service.interfaces.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantService restaurantService;
    private final UserService userService;

    public VoteServiceImpl(VoteRepository voteRepository, UserRepository userRepository, RestaurantService restaurantService, UserService userService) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantService = restaurantService;
        this.userService = userService;
    }

    @Override
    public Vote save(Long idRestaurant) {
        UserSS loggedUser = UserServiceSS.authenticated();
        User user = userRepository.findByEmail(loggedUser.getUsername());
        Restaurant restaurant = restaurantService.findById(idRestaurant);
        LocalDate date = LocalDate.now();

        Vote hasAlreadyVotedToday = voteRepository.findAllByDateAndUserId(date, user.getId());
        if (hasAlreadyVotedToday == null) {
            Vote vote = new Vote(user, restaurant, date);
            return createVote(vote);
        }
        return updateVote(hasAlreadyVotedToday, restaurant, user);

    }

    private Vote updateVote(Vote hasAlreadyVotedToday, Restaurant restaurant, User user) {
        Vote vote = new Vote();
        vote.setId(hasAlreadyVotedToday.getId());
        vote.setDate(hasAlreadyVotedToday.getDate());
        vote.setUser(user);
        vote.setRestaurant(restaurant);
        return voteRepository.save(vote);

    }


    private Vote createVote(Vote vote) {
        return voteRepository.save(vote);
    }

}
