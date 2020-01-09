package com.rodrigoramos.personalproject.repository;

import com.rodrigoramos.personalproject.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Vote findByIdAndDate(Long id, LocalDate date);

}
