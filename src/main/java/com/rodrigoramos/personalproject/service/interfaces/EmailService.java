package com.rodrigoramos.personalproject.service.interfaces;

import com.rodrigoramos.personalproject.model.User;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(User user, String newPass);
}
