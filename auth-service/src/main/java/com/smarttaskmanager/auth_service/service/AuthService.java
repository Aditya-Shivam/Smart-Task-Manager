package com.smarttaskmanager.auth_service.service;

import com.smarttaskmanager.auth_service.dto.AuthResponse;
import com.smarttaskmanager.auth_service.dto.SignupRequest;
import com.smarttaskmanager.auth_service.model.User;
import com.smarttaskmanager.auth_service.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthResponse registerUser(SignupRequest request) {
        // Check duplicates
        if (userRepo.existsByEmail(request.getEmail())) {
            return new AuthResponse("Email already in use", null);
        }
        if (userRepo.existsByUsername(request.getUsername())) {
            return new AuthResponse("Username already taken", null);
        }

        // Create new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepo.save(user);

        return new AuthResponse("User registered successfully!", null);
    }

    public ResponseEntity<Boolean> getUserByUserName(String userName) {

        System.out.println(userName + "+_))()((()))");
             User user = userRepo.findByUsername(userName);
            System.out.println(user+"&*&*&*&*");
             if(user == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
            return ResponseEntity.status(HttpStatus.OK).body(true);


    }
}
