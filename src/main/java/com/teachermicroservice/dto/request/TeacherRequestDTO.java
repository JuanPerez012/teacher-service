package com.teachermicroservice.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public record TeacherRequestDTO(
    @NotBlank @Size(max = 50) String firstName,
    @NotBlank @Size(max = 50) String lastName,
    @NotBlank @Email @Size(max = 100) String email,
    LocalDate birthDate,
    @NotNull String gender,
    @Size(max = 20) String phone,
    @Size(max = 150) String address,

    @NotBlank @Size(max = 120) String specialty,
    @NotNull LocalDate hireDate,
    @DecimalMin(value = "0.0", inclusive = true) BigDecimal salary,
    @Size(max = 100) String academicRank,
    @Size(max = 100) String department
) {}