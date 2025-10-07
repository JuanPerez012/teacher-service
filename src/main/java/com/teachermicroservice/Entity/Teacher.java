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

    @Column(name = "hire_date")
    private LocalDate hireDate;

    public Long getId() { return id; }
    public Long getPersonId() { return personId; }
    public String getSpecialty() { return specialty; }

    public LocalDate getHireDate() { return hireDate; }

    public void setId(Long id) { this.id = id; }
    public void setPersonId(Long personId) { this.personId = personId; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    public void setHireDate(LocalDate hireDate) { this.hireDate = hireDate; }

}


