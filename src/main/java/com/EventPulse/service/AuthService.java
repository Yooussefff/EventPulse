package com.EventPulse.service;

import com.EventPulse.dto.request.LoginRequest;
import com.EventPulse.dto.request.SignupRequest;
import com.EventPulse.dto.response.AuthResponse;
import com.EventPulse.entities.User;
import com.EventPulse.entities.enums.Role;
import com.EventPulse.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    public AuthResponse signup(SignupRequest request) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        if(userRepository.findByUsername(request.getUserName()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .userName(request.getUserName())
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.generateToken(user))
                .email(user.getEmail())
                .name(user.getName())
                .userName(user.getUserName())
                .role(user.getRole())
                .build();
    }

    public AuthResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        return AuthResponse.builder()
                .token(jwtService.generateToken(user))
                .email(user.getEmail())
                .name(user.getName())
                .userName(user.getUserName())
                .role(user.getRole())
                .build();
    }
}
