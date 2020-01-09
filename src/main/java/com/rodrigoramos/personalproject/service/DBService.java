package com.rodrigoramos.personalproject.service;

import com.rodrigoramos.personalproject.model.Restaurant;
import com.rodrigoramos.personalproject.model.User;
import com.rodrigoramos.personalproject.model.Vote;
import com.rodrigoramos.personalproject.model.enums.Profile;
import com.rodrigoramos.personalproject.repository.RestaurantRepository;
import com.rodrigoramos.personalproject.repository.UserRepository;
import com.rodrigoramos.personalproject.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;

@Service
public class DBService {

    private final BCryptPasswordEncoder bc;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final VoteRepository voteRepository;

    @Autowired
    public DBService(UserRepository userRepository, BCryptPasswordEncoder bc, RestaurantRepository restaurantRepository, VoteRepository voteRepository) {
        this.userRepository = userRepository;
        this.bc = bc;
        this.restaurantRepository = restaurantRepository;
        this.voteRepository = voteRepository;
    }

    public void instantiateTestDatabase() throws ParseException {

        User jose = new User(null, "Rodrigo", "Ramos", "grohlbr@gmail.com", "42597178048", bc.encode("123456"),null);
        User michael = new User(null,"Michael", "Jackson", "mic@terra.com.br", "08108376092",bc.encode("123456"),null);
        User nicolas = new User( null, "Nicolas", "Cage", "nic@terra.com.br", "87710225039", bc.encode("123456"),null);
        User robert = new User( null, "Robert", "de Niro", "rob@terra.com.br", "02780251026", bc.encode("123456"),null);
        User tom = new User(null, "Tom", "Jones", "jon@terra.com.br", "93972440006", bc.encode("123456"),null);
        User angelina = new User( null, "Angelina", "Jolie", "ang@terra.com.br", "18007416005",bc.encode("123456"), null);
        User fernanda = new User(null, "Fernanda", "Montenegro", "fer@terra.com.br", "99740482066",bc.encode("123456"), null);

        Restaurant r1 = new Restaurant(null,"Pizza Hut");
        Restaurant r2 = new Restaurant(null,"Didge Steakhouse Pub");
        Restaurant r3 = new Restaurant(null,"La Rouge Bistrô");
        Restaurant r4 = new Restaurant(null,"Atelier de Massas");
        Restaurant r5 = new Restaurant(null,"Guacamole Cocina Mexicana");



        jose.addPerfil(Profile.ADMIN);
        userRepository.saveAll(Arrays.asList(jose, michael, nicolas, robert, tom, angelina, fernanda));
        restaurantRepository.saveAll(Arrays.asList(r1,r2,r3,r4,r5));


    }
}
