package com.smarttask.taskservice.controller;


import com.smarttask.taskservice.feign.AuthInterface;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    @Autowired
    AuthInterface authInterface;

    @GetMapping("/getAllTasks")
    public ResponseEntity<String>
    getAllTask(@RequestHeader("Authorization") String token){
        try {
            ResponseEntity<?> response = authInterface.validateToken(token);
            System.out.println("Auth Response: " + response);

            // Add your logic to handle the response if needed
            return  ResponseEntity.ok("Authorized - Task list here...");

        } catch (FeignException.Unauthorized ex) {
            System.out.println("Unauthorized: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized - Invalid token");
        } catch (FeignException ex) {
            System.out.println("Feign error: " + ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("Auth service error");
        } catch (Exception e) {
            System.out.println("Other error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error");
        }
    }
}
