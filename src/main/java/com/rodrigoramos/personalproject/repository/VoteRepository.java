package com.rodrigoramos.personalproject.repository;

import com.rodrigoramos.personalproject.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    @Transactional
    Vote findByIdAndDate(Long id, LocalDate date);

    Vote findAllByDateAndUserId(LocalDate date, Long id);

}
