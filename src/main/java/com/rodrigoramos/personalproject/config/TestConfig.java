package com.rodrigoramos.personalproject.config;

import com.rodrigoramos.personalproject.service.DBService;
import com.rodrigoramos.personalproject.service.SmtpEmailService;
import com.rodrigoramos.personalproject.service.interfaces.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    private final DBService dbService;

    @Autowired
    public TestConfig(DBService dbService) {
        this.dbService = dbService;
    }


    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }

}