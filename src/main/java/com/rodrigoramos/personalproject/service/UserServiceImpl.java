package com.rodrigoramos.personalproject.service;

import com.rodrigoramos.personalproject.dto.UserDTO;
import com.rodrigoramos.personalproject.model.User;
import com.rodrigoramos.personalproject.repository.UserRepository;
import com.rodrigoramos.personalproject.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
