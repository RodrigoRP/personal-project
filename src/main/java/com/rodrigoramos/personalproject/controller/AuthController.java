package com.rodrigoramos.personalproject.controller;

import com.rodrigoramos.personalproject.dto.EmailDTO;
import com.rodrigoramos.personalproject.security.JWTUtil;
import com.rodrigoramos.personalproject.security.UserSS;
import com.rodrigoramos.personalproject.service.AuthService;
import com.rodrigoramos.personalproject.service.UserServiceSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private final JWTUtil jwtUtil;
    private final AuthService service;

    @Autowired
    public AuthController(JWTUtil jwtUtil, AuthService service) {
        this.jwtUtil = jwtUtil;
        this.service = service;
    }

    @PostMapping(value = "/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = UserServiceSS.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/forgot")
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
        service.sendNewPassword(objDto.getEmail());
        return ResponseEntity.noContent().build();
    }
}
