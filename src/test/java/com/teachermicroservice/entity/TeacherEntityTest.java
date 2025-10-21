package com.teachermicroservice.entity;

import com.teachermicroservice.enums.Gender;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class TeacherEntityTest {

    @Test
    void gettersAndSetters_coverAllFields() {
        Teacher teacher = new Teacher();

        teacher.setId(10L);
        teacher.setFirstName("Ada");
        teacher.setLastName("Lovelace");
        teacher.setEmail("ada.lovelace@example.com");
        teacher.setBirthDate(LocalDate.of(1990, 12, 10));
        teacher.setGender(Gender.FEMENINO);
        teacher.setPhone("3001234567");
        teacher.setAddress("Calle 123 #45-67");
        teacher.setSpecialty("Ingeniería de Software");
        teacher.setHireDate(LocalDate.of(2020, 1, 15));
        teacher.setSalary(new BigDecimal("8500.00"));
        teacher.setAcademicRank("Titular");
        teacher.setDepartment("Ciencias de la Computación");

        assertEquals(10L, teacher.getId());
        assertEquals("Ada", teacher.getFirstName());
        assertEquals("Lovelace", teacher.getLastName());
        assertEquals("ada.lovelace@example.com", teacher.getEmail());
        assertEquals(LocalDate.of(1990, 12, 10), teacher.getBirthDate());
        assertEquals(Gender.FEMENINO, teacher.getGender());
        assertEquals("3001234567", teacher.getPhone());
        assertEquals("Calle 123 #45-67", teacher.getAddress());
        assertEquals("Ingeniería de Software", teacher.getSpecialty());
        assertEquals(LocalDate.of(2020, 1, 15), teacher.getHireDate());
        assertEquals(new BigDecimal("8500.00"), teacher.getSalary());
        assertEquals("Titular", teacher.getAcademicRank());
        assertEquals("Ciencias de la Computación", teacher.getDepartment());
    }
}
