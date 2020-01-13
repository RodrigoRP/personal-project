package com.rodrigoramos.personalproject.repository;

import com.rodrigoramos.personalproject.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {


    Vote findAllByDateAndUserId(LocalDate date, Long id);

    Long countAllByRestaurantId(Long restaurantId);

}
