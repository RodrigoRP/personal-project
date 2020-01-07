package com.rodrigoramos.personalproject.service;

import com.rodrigoramos.personalproject.model.User;
import com.rodrigoramos.personalproject.repository.UserRepository;
import com.rodrigoramos.personalproject.service.exceptions.ObjectNotFoundException;
import com.rodrigoramos.personalproject.service.interfaces.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private EmailService emailService;

    private Random rand = new Random();

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void sendNewPassword(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null) {
            throw new ObjectNotFoundException("E-mail not found!");
        }
        String newPass = newPassword();
        user.setPassword(bCryptPasswordEncoder.encode(newPass));

        userRepository.save(user);
        emailService.sendNewPasswordEmail(user, newPass);
    }

    private String newPassword() {
        char[] vet = new char[10];
        for (int i=0; i<10; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar() {
        int opt = rand.nextInt(3);
        if (opt == 0) { //
            return (char) (rand.nextInt(10) + 48);
        }
        else if (opt == 1) {
            return (char) (rand.nextInt(26) + 65);
        }
        else {
            return (char) (rand.nextInt(26) + 97);
        }
    }
}
