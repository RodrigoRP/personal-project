package com.rodrigoramos.personalproject.service;

import com.rodrigoramos.personalproject.model.User;
import com.rodrigoramos.personalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;

@Service
public class DBService {

    private final UserRepository userRepository;

    @Autowired
    public DBService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void instantiateTestDatabase() throws ParseException {

        User jose = new User(null, "Jose", "Silva", "jose@terra.com.br", "42597178048", null);
        User michael = new User(null,"Michael", "Jackson", "mic@terra.com.br", "08108376092",null);
        User nicolas = new User( null, "Nicolas", "Cage", "nic@terra.com.br", "87710225039", null);
        User robert = new User( null, "Robert", "de Niro", "rob@terra.com.br", "02780251026", null);
        User tom = new User(null, "Tom", "Jones", "jon@terra.com.br", "93972440006", null);
        User angelina = new User( null, "Angelina", "Jolie", "ang@terra.com.br", "18007416005", null);
        User fernanda = new User(null, "Fernanda", "Montenegro", "fer@terra.com.br", "99740482066", null);

        userRepository.saveAll(Arrays.asList(jose, michael, nicolas, robert, tom, angelina, fernanda));

    }
}
