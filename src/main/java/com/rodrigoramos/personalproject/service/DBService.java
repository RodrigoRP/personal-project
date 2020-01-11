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

        User n1 = new User(null, "Grohu", "Ramosson", "n1@terra.com.br", "29863789003",bc.encode("123456"), null);
        User n2 = new User(null, "Grahar", "R4m60R0d05", "n2@terra.com.br", "73103603037",bc.encode("123456"), null);
        User n3 = new User(null, "Rauisel", "RamgoBipya", "n3@terra.com.br", "76023906010",bc.encode("123456"), null);
        User n4 = new User(null, "Gluserrgak", "Ramosmann", "n4@terra.com.br", "08415606095",bc.encode("123456"), null);
        User n5 = new User(null, "Guligamph", "Rodrigodesu", "n5@terra.com.br", "47013679070",bc.encode("123456"), null);
        User n6 = new User(null, "Deton", "Ramoschenko", "n6@terra.com.br", "63237855014",bc.encode("123456"), null);
        User n7 = new User(null, "Woema", "HeiRodrigo", "n7@terra.com.br", "30940104075",bc.encode("123456"), null);
        User n8 = new User(null, "Noanlaol", "Ramoslitz", "n8@terra.com.br", "14852524076",bc.encode("123456"), null);
        User n9 = new User(null, "Lirmedou", "VanRamos", "n9@terra.com.br", "34652925026",bc.encode("123456"), null);
        User n10 = new User(null, "Wewya", "Ramostrom", "n10@terra.com.br", "65536054082",bc.encode("123456"), null);
        User n11 = new User(null, "Kreinna", "Ramoslitz", "n11@terra.com.br", "88111151080",bc.encode("123456"), null);
        User n12 = new User(null, "Isbor", "Ramosama", "n12@terra.com.br", "10540486060",bc.encode("123456"), null);


        Restaurant r1 = new Restaurant(null,"Pizza Hut");
        Restaurant r2 = new Restaurant(null,"Didge Steakhouse Pub");
        Restaurant r3 = new Restaurant(null,"La Rouge Bistrô");
        Restaurant r4 = new Restaurant(null,"Atelier de Massas");
        Restaurant r5 = new Restaurant(null,"Guacamole Cocina Mexicana");


  /*      Vote v1 = new Vote(jose, r1, LocalDate.now());
        Vote v2 = new Vote(michael, r2, LocalDate.now());
        Vote v3 = new Vote(nicolas, r3, LocalDate.now());
        Vote v4 = new Vote(tom, r3, LocalDate.now());
        Vote v5 = new Vote(fernanda, r1, LocalDate.now());*/




        jose.addPerfil(Profile.ADMIN);
        userRepository.saveAll(Arrays.asList(jose, michael, nicolas, robert, tom, angelina, fernanda,
                n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12));
        restaurantRepository.saveAll(Arrays.asList(r1,r2,r3,r4,r5));
        //voteRepository.saveAll(Arrays.asList(v1,v2,v4,v3,v5));


    }
}
