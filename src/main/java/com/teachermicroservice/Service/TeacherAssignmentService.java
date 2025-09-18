package com.teachermicroservice.Service;

import com.teachermicroservice.Entity.Teacher;
import com.teachermicroservice.Entity.TeacherAssignment;

import java.time.LocalDateTime;
import java.util.List;

public interface TeacherAssignmentService {
    TeacherAssignment create(Teacher teacher, Long subjectId, Long groupId, String period, Boolean primary);
    TeacherAssignment getById(Long id);
    List<TeacherAssignment> listAll();
    List<TeacherAssignment> filterByTeacher(Long teacherId);
    TeacherAssignment update(Long id, Long subjectId, Long groupId, String period, Boolean primary);
    void delete(Long id);
}


