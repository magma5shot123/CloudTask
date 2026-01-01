package com.example.CloudeTask.controller;

import com.example.CloudeTask.dto.auth.AuthResponse;
import com.example.CloudeTask.dto.auth.LoginRequest;
import com.example.CloudeTask.dto.auth.RegisterRequest;
import com.example.CloudeTask.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@Valid @RequestBody LoginRequest dto) {
        return authService.login(dto);
    }

    @PostMapping("/register")
    public AuthResponse register(@Valid @RequestBody RegisterRequest dto) {
        return authService.register(dto);
    }
}
