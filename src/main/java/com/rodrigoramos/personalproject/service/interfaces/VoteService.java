package com.rodrigoramos.personalproject.service.interfaces;

import com.rodrigoramos.personalproject.model.Vote;

import java.time.Clock;
import java.time.LocalTime;
import java.time.ZoneId;

public interface VoteService {

    LocalTime EXPIRATION_TIME = LocalTime.of(23, 30);
    ZoneId ZONE_ID = ZoneId.of("America/Sao_Paulo");

    Vote save(Long restaurant);
}
