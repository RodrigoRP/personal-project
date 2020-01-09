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

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserService userService;

    @Override
    public Vote save(Long idRestaurant) {
        UserSS loggedUser = UserServiceSS.authenticated();
        User user = userRepository.findByEmail(loggedUser.getUsername());
        Restaurant restaurant = restaurantService.findById(idRestaurant);
        LocalDate date = LocalDate.now();
        Vote hasAlreadyVotedToday = voteRepository.findByIdAndDate(loggedUser.getId(),date);

        if(hasAlreadyVotedToday == null) {
            Vote vote = new Vote(user, restaurant, date);
            return createVote(vote);
        }

        return updateVote(hasAlreadyVotedToday, restaurant);



      //  Vote vote = new Vote(user, restaurant, date);
       // return voteRepository.save(vote);
    }

    private Vote updateVote(Vote hasAlreadyVotedToday, Restaurant restaurant) {
         Vote vote = new Vote();
         vote.setId(hasAlreadyVotedToday.getId());
         vote.setDate(hasAlreadyVotedToday.getDate());
         vote.setUser(hasAlreadyVotedToday.getUser());
         vote.setRestaurant(restaurant);
         return voteRepository.save(vote);

    }


    private Vote createVote(Vote vote) {
        return voteRepository.save(vote);
    }

    /* public void vote() {
         this.checkVotingAvailability(LocalTime.now(VoteService.ZONE_ID));

         User user = userRepository.findByEmail(UserServiceSS.authenticated().getUsername());
         if (hasAlreadyVotedToday(user.getVote())) {
             updateVote(rest_id, employee);
         } else {
             createVote(rest_id, employee);
         }

     }

     private void createVote(Integer rest_id, User user) {
         Vote vote = new Vote(employee);
         vote.setRestaurant_id(rest_id);
         vote.setDateUpdated(LocalDate.now(VoteServiceInterface.ZONE_ID));

         employee.setVote(vote);
         employeeRepository.save(employee);
     }

     public void checkVotingAvailability(LocalTime currentTime) {
         if (currentTime.isAfter(VoteService.EXPIRATION_TIME)) {
             throw new VotingIsUnavailableException("Horario fora");
         }
     }

 */
 /*   private boolean hasAlreadyVotedToday(Vote vote) {
        return vote.getDateUpdated().isEqual(LocalDate.now(VoteService.ZONE_ID));
    }
*/
}
