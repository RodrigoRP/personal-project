package com.rodrigoramos.personalproject.controller;

import com.rodrigoramos.personalproject.dto.UserDTO;
import com.rodrigoramos.personalproject.model.User;
import com.rodrigoramos.personalproject.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<User> save(@RequestBody UserDTO dto) {
        User user = userService.save(dto.ConvertDtoToEntity());
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


}
