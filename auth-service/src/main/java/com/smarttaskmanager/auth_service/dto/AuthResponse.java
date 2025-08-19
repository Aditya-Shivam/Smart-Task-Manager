package com.smarttaskmanager.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {
    private String message;
    private String token; // will be null for now, later filled with JWT
}
