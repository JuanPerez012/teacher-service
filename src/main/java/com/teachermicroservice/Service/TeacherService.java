package com.teachermicroservice.Service;

import com.teachermicroservice.Entity.Teacher;
import com.teachermicroservice.Entity.TeacherAssignment;

import java.time.LocalDate;
import java.util.List;

public interface TeacherService {
    Teacher create(Long personId, String specialty, String status, LocalDate hireDate);
    Teacher getById(Long id);
    List<Teacher> listAll();
    List<Teacher> filterBySpecialty(String specialty);
    List<Teacher> filterByStatus(String status);
    Teacher update(Long id, String specialty, String status, LocalDate hireDate);
    void delete(Long id);
}


