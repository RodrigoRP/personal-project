package com.rodrigoramos.personalproject.service;

import com.rodrigoramos.personalproject.dto.UserDTO;
import com.rodrigoramos.personalproject.dto.UserResponseDTO;
import com.rodrigoramos.personalproject.dto.UserUpdateDTO;
import com.rodrigoramos.personalproject.model.User;
import com.rodrigoramos.personalproject.model.enums.Profile;
import com.rodrigoramos.personalproject.repository.UserRepository;
import com.rodrigoramos.personalproject.security.UserSS;
import com.rodrigoramos.personalproject.service.exceptions.AuthorizationException;
import com.rodrigoramos.personalproject.service.exceptions.ObjectNotFoundException;
import com.rodrigoramos.personalproject.service.interfaces.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public User convertDtoToEntity(UserDTO dto) {
        return new User(null, dto.getFirstName(), dto.getLastName(), dto.getEmail(), dto.getCpf(), null, null);
    }

    @Override
    public UserDTO convertEntityToDto(User user) {
        return new UserDTO(user.getFirstName(), user.getLastName(), user.getEmail(), user.getCpf(), null);
    }

    @Override
    public UserResponseDTO convertDTO(User user) {
        return new UserResponseDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getAdmin());
    }

    public User convertUpdateDtoToEntity(UserUpdateDTO objDto) {
        return new User(null, objDto.getFirstName(), objDto.getLastName(), objDto.getEmail(), null, null, null);
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new ObjectNotFoundException("Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + User.class.getName());
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        UserSS user = UserServiceSS.authenticated();
        if (user == null || !user.hasRole(Profile.ADMIN) && !id.equals(user.getId())) {
            throw new AuthorizationException("Acesso negado");
        }

        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));

    }

    @Override
    public void deleteUserByEmail(String email) {
        findByEmail(email);
        userRepository.deleteByEmail(email);
    }


    public User updateUser(User obj) {
        User newObj = findById(obj.getId());
        updateData(newObj, obj);
        return userRepository.save(newObj);
    }

    private void updateData(User newObj, User obj) {
        if (obj.getFirstName() != null) {
            newObj.setFirstName(obj.getFirstName());
        }
        if (obj.getLastName() != null) {
            newObj.setLastName(obj.getLastName());
        }
        if (obj.getEmail() != null) {
            newObj.setEmail(obj.getEmail());
        }
    }
}
