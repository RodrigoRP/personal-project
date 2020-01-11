package com.rodrigoramos.personalproject.repository;

import com.rodrigoramos.personalproject.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {


    Vote findAllByDateAndUserId(LocalDate date, Long id);

/*    Long countAllByDate(LocalDate date);

    Long countByDate(LocalDate date);

    Long countAllByRestaurant(LocalDate date);*/

    Long countAllByRestaurantId(Long restaurantId);

  /*  @Query(value = "SELECT restaurant_id, COUNT(restaurant_id) FROM vote GROUP BY restaurant_id HAVING COUNT (restaurant_id)=( SELECT MAX(mycount) FROM (SELECT restaurant_id, COUNT(restaurant_id) mycount FROM vote GROUP BY restaurant_id))", nativeQuery = true)
    Vote countAllVotesOfTheDay();
*/


}
