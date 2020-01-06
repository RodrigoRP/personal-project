package com.rodrigoramos.personalproject.controller;

import com.rodrigoramos.personalproject.dto.UserDTO;
import com.rodrigoramos.personalproject.dto.UserResponseDTO;
import com.rodrigoramos.personalproject.model.User;
import com.rodrigoramos.personalproject.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping(value = "/")
    public ResponseEntity<UserResponseDTO> save(@RequestBody UserDTO dto) {
        User user = userService.save(userService.convertDtoToEntity(dto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<User> userList = userService.findAll();
        List<UserResponseDTO> employeeDTOList = userList.stream().map(obj -> new UserResponseDTO(obj))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(employeeDTOList);
    }


}
