package com.teachermicroservice.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TeacherResponseDTO(
        Long id,
        String specialty,
    LocalDate hireDate,
    BigDecimal salary,
    String academicRank,
    String department
) {}