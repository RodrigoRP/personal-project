package com.rodrigoramos.personalproject.service;

import com.rodrigoramos.personalproject.model.Restaurant;
import com.rodrigoramos.personalproject.model.User;
import com.rodrigoramos.personalproject.model.Vote;
import com.rodrigoramos.personalproject.repository.UserRepository;
import com.rodrigoramos.personalproject.repository.VoteRepository;
import com.rodrigoramos.personalproject.security.UserSS;
import com.rodrigoramos.personalproject.service.interfaces.RestaurantService;
import com.rodrigoramos.personalproject.service.interfaces.VoteService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VoteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantService restaurantService;

    public VoteServiceImpl(VoteRepository voteRepository, UserRepository userRepository, RestaurantService restaurantService) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantService = restaurantService;
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

    @Override
    public Long countVotesByRestaurant(Long restaurantId) {
        return voteRepository.countAllByRestaurantId(restaurantId);
    }

    @Override
    public Restaurant winnerToday() {
        Map<Long, Long> map;
        map = winnerOfTheDay();
        Long idRestaurantWinner = maxUsingStreamAndLambda(map);
        return restaurantService.findById(idRestaurantWinner);
    }

    public <K, V extends Comparable<V>> V maxUsingStreamAndLambda(Map<K, V> map) {
        Optional<Map.Entry<K, V>> maxEntry = map.entrySet()
                .stream()
                .max((Map.Entry<K, V> e1, Map.Entry<K, V> e2) -> e1.getValue()
                        .compareTo(e2.getValue())
                );

        return (V) maxEntry.get().getKey();
    }

    public Map<Long, Long> winnerOfTheDay() {
        Map<Long, Long> map = new HashMap<>();
        List<Restaurant> rest = restaurantService.findAll();

        for (int i = 0; i < rest.size(); i++) {
            Long votos = countVotesByRestaurant(rest.get(i).getId());
            map.put(rest.get(i).getId(), votos);
        }
        return map;
    }


}
