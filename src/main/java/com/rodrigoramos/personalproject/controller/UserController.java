package com.rodrigoramos.personalproject.controller;

import com.rodrigoramos.personalproject.dto.UserDTO;
import com.rodrigoramos.personalproject.dto.UserResponseDTO;
import com.rodrigoramos.personalproject.model.User;
import com.rodrigoramos.personalproject.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<UserResponseDTO> save(@RequestBody UserDTO dto) {
        User user = userService.save(userService.convertDtoToEntity(dto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/")
    public ResponseEntity<List<UserResponseDTO>> findAll() {
        List<User> userList = userService.findAll();
        List<UserResponseDTO> employeeDTOList = userList.stream().map(obj -> new UserResponseDTO(obj))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(employeeDTOList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable Long id) {
        User user = userService.findById(id);
        UserResponseDTO responseDTO = userService.convertDTO(user);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<UserResponseDTO> findByEmail(@PathVariable String email) {
        User user = userService.findByEmail(email);
        UserResponseDTO responseDTO = userService.convertDTO(user);
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping(value = "/email/{email}")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email) {
        userService.deleteUserByEmail(email);
        return ResponseEntity.noContent().build();

    }

}
