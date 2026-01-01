package com.example.CloudeTask.service;

import com.example.CloudeTask.dto.auth.AuthResponse;
import com.example.CloudeTask.dto.auth.LoginRequest;
import com.example.CloudeTask.dto.auth.RegisterRequest;
import com.example.CloudeTask.entity.Company;
import com.example.CloudeTask.entity.User;
import com.example.CloudeTask.repository.CompanyRepository;
import com.example.CloudeTask.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.companyRepository = companyRepository;
    }

    public AuthResponse login(LoginRequest dto) {
        Optional<User> userOptional = userRepository.findByUsername(dto.username());
        if (userOptional.isEmpty()) {
            return new AuthResponse(false, "Invalid username or password");
        }
        User user = userOptional.get();
        if (dto.password() == null || !passwordEncoder.matches(dto.password(), user.getPassword())) {
            return new AuthResponse(false, "Invalid username or password");
        }

        return new AuthResponse(true, "Login Successful");
    }

    public AuthResponse register(RegisterRequest dto) {
        Optional<User> usernameOptional = userRepository.findByUsername(dto.username());
        if (usernameOptional.isPresent()) {
            return new AuthResponse(false, "Username already exists");
        }
        Optional<User> emailOptional = userRepository.findByEmail(dto.email());
        if (emailOptional.isPresent()) {
            return new AuthResponse(false, "Email already exists");
        }
        if (!dto.password().equals(dto.secondPassword())) {
            return new AuthResponse(false, "Passwords do not match");
        }

        String encodedPassword = passwordEncoder.encode(dto.password());
        Company company = companyRepository.findById(dto.companyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));
        userRepository.save(new User(dto.username(), dto.email(), encodedPassword, company));

        return new AuthResponse(true, "Register Successful");
    }

    public User getCurrentUser() {
        return userRepository.findAll().stream().findFirst().orElse(null);
    }
}
