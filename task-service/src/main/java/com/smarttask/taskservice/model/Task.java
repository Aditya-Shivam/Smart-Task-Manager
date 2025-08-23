package com.smarttask.taskservice.model;

import com.smarttask.taskservice.model.Priority;
import com.smarttask.taskservice.model.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 2000)
    private String description;

    private LocalDate assignedDate;   // format: DD/MM/YY (frontend can handle formatting)

    private LocalDate dueDate;

    private String assignedTo;  // username from AuthService

    private String assignedBy;  // username from JWT

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TaskStatus status = TaskStatus.PENDING; // PENDING, IN_PROGRESS, COMPLETED, ARCHIVED


    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Priority priority = Priority.LOW; // LOW, MEDIUM, HIGH, CRITICAL

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
