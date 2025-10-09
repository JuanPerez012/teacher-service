package com.teachermicroservice.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TeacherResponseDTO(
    Long id,
    String firstName,
    String lastName,
    String email,
    String gender,
    String phone,
    String address,
    String specialty,
    LocalDate hireDate,
    BigDecimal salary,
    String academicRank,
    String department
) {}