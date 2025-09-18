package com.teachermicroservice.Repository;

import com.teachermicroservice.Entity.TeacherAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherAssignmentRepository extends JpaRepository<TeacherAssignment, Long> {
    List<TeacherAssignment> findByTeacherId(Long teacherId);
    List<TeacherAssignment> findBySubjectIdAndPeriod(Long subjectId, String period);
    List<TeacherAssignment> findByGroupIdAndPeriod(Long groupId, String period);
    boolean existsByTeacherIdAndSubjectIdAndGroupIdAndPeriod(Long teacherId, Long subjectId, Long groupId, String period);
}


