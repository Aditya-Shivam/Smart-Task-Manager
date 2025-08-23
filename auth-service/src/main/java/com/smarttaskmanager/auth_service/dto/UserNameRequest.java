package com.smarttaskmanager.auth_service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UserNameRequest {
    @NotBlank(message = "userName cannot be empty")
    private String userName;
}
