package com.teachermicroservice.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TeacherEntityTest {

    @Test
    void gettersAndSetters_coverAllFields() {
        Teacher teacher = new Teacher();

        teacher.setId(10L);
        teacher.setSpecialty("Ingeniería de Software");
        teacher.setHireDate(LocalDate.of(2020, 1, 15));
        teacher.setSalary(new BigDecimal("8500.00"));
        teacher.setAcademicRank("Titular");
        teacher.setDepartment("Ciencias de la Computación");

        assertEquals(10L, teacher.getId());
        assertEquals("Ingeniería de Software", teacher.getSpecialty());
        assertEquals(LocalDate.of(2020, 1, 15), teacher.getHireDate());
        assertEquals(new BigDecimal("8500.00"), teacher.getSalary());
        assertEquals("Titular", teacher.getAcademicRank());
        assertEquals("Ciencias de la Computación", teacher.getDepartment());
    }
}
