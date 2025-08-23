package com.smarttaskmanager.auth_service.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TokenValidationResponse {
    private boolean valid;
    private String username;
    private String role;
}
