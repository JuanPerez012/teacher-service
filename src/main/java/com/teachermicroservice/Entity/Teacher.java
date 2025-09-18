package com.teachermicroservice.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.*;

@Entity
@Table(name = "teachers",
        uniqueConstraints = @UniqueConstraint(name = "uk_teacher_person", columnNames = "person_id"))
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "person_id", nullable = false)
    private Long personId;

    @NotBlank
    @Size(max = 120)
    @Column(nullable = false, length = 120)
    private String specialty;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String status;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        if (this.status == null || this.status.isBlank()) {
            this.status = "ACTIVE";
        }
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Long getPersonId() { return personId; }
    public String getSpecialty() { return specialty; }
    public String getStatus() { return status; }
    public LocalDate getHireDate() { return hireDate; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setId(Long id) { this.id = id; }
    public void setPersonId(Long personId) { this.personId = personId; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }
    public void setStatus(String status) { this.status = status; }
    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}


