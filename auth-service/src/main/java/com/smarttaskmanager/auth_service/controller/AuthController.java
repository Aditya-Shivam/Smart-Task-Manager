package com.smarttaskmanager.auth_service.controller;

import com.smarttaskmanager.auth_service.dto.*;
import com.smarttaskmanager.auth_service.model.UserPrincipal;
import com.smarttaskmanager.auth_service.service.AuthService;
import com.smarttaskmanager.auth_service.service.MyUserDetailsService;
import com.smarttaskmanager.auth_service.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody SignupRequest request) {
        return ResponseEntity.ok(authService.registerUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            final UserPrincipal user = userDetailsService.loadUserByUsername(request.getUsername());
            final String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

            return ResponseEntity.ok(Map.of("token", token));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token) {
        try {
            // Remove "Bearer " if present
            if (token.startsWith("Bearer ")) {
                token = token.substring(7);
            }

            boolean isValid = jwtUtil.validateToken(token);
            String username = jwtUtil.extractUsername(token);
            String role = jwtUtil.extractRole(token);

            return ResponseEntity.ok(new TokenValidationResponse(true, username, role));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(new TokenValidationResponse(false, null, null));
        }

    }

    @GetMapping("/getUser")
    public ResponseEntity<Boolean> getUserByUserName(@RequestBody UserNameRequest user){
        System.out.println(user.getUserName());
        return authService.getUserByUserName(user.getUserName());
    }
}
