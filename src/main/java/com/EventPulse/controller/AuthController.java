package com.EventPulse.controller;

import com.EventPulse.dto.request.EmailRequest;
import com.EventPulse.dto.request.LoginRequest;
import com.EventPulse.dto.request.SignupRequest;
import com.EventPulse.dto.response.ApiResponse;
import com.EventPulse.dto.response.AuthResponse;
import com.EventPulse.service.AuthService;
import com.EventPulse.service.OTPService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final OTPService otpService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<AuthResponse>> signup(@RequestBody SignupRequest signupRequest) {
        AuthResponse authResponse = authService.signup(signupRequest);
        return ResponseEntity.ok(new ApiResponse<>(authResponse));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = authService.login(loginRequest);
        return ResponseEntity.ok(new ApiResponse<>(authResponse));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse<String>> forgotPassword(@RequestBody @Valid EmailRequest request) {
        otpService.sendPasswordResetOtp(request.getEmail());
        return ResponseEntity.ok(new ApiResponse<>("Password reset OTP sent to email."));
    }
}
