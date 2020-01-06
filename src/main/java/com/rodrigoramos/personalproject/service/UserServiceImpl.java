package com.rodrigoramos.personalproject.service;

import com.rodrigoramos.personalproject.dto.UserDTO;
import com.rodrigoramos.personalproject.dto.UserResponseDTO;
import com.rodrigoramos.personalproject.model.User;
import com.rodrigoramos.personalproject.repository.UserRepository;
import com.rodrigoramos.personalproject.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public User convertDtoToEntity(UserDTO dto){
        return new User(null, dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getCpf(), null);
    }

    @Override
    public UserDTO convertEntityToDto(User user) {
        return new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail(), user.getCpf());
    }

    public static UserResponseDTO convertDTO(User user) {
        return new UserResponseDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAdmin());
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
