package com.example.infomansys.service;

import com.example.infomansys.config.JwtUtil;
import com.example.infomansys.dto.*;
import com.example.infomansys.entity.User;
import com.example.infomansys.exception.DuplicateResourceException;
import com.example.infomansys.exception.ResourceNotFoundException;
import com.example.infomansys.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {
    User user = userRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> new ResourceNotFoundException("Invalid username or password"));

    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
        throw new ResourceNotFoundException("Invalid username or password");
    }

    String token = jwtUtil.generateToken(user.getUsername());

    return LoginResponse.builder()
            .token(token)
            .username(user.getUsername())
            .build();
}

    public void register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new DuplicateResourceException(
                "Username '" + request.getUsername() + "' is already taken");
    }

    User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .build();

    userRepository.save(user);
    }
}