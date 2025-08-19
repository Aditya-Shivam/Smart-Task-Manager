package com.smarttaskmanager.auth_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "Email is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}
