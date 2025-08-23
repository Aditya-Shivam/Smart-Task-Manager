package com.smarttask.taskservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("auth-service")
public interface AuthInterface {
    @GetMapping("/api/auth/validate")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String token);
}
