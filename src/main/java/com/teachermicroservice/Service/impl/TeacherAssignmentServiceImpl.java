package com.teachermicroservice.Service.impl;

import com.teachermicroservice.Entity.Teacher;
import com.teachermicroservice.Entity.TeacherAssignment;
import com.teachermicroservice.Repository.TeacherAssignmentRepository;
import com.teachermicroservice.Service.TeacherAssignmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeacherAssignmentServiceImpl implements TeacherAssignmentService {

    private final TeacherAssignmentRepository teacherAssignmentRepository;

    public TeacherAssignmentServiceImpl(TeacherAssignmentRepository teacherAssignmentRepository) {
        this.teacherAssignmentRepository = teacherAssignmentRepository;
    }

    @Override
    public TeacherAssignment create(Teacher teacher, Long subjectId, Long groupId, String period, Boolean primary) {
        TeacherAssignment t = new TeacherAssignment();
        t.setTeacher(teacher);
        t.setSubjectId(subjectId);
        t.setGroupId(groupId);
        t.setPeriod(period);
        t.setPrimary(primary != null && primary);
        return teacherAssignmentRepository.save(t);
    }

    @Override
    @Transactional(readOnly = true)
    public TeacherAssignment getById(Long id) {
        return teacherAssignmentRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeacherAssignment> listAll() {
        return teacherAssignmentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeacherAssignment> filterByTeacher(Long teacherId) {
        return teacherAssignmentRepository.findByTeacherId(teacherId);
    }

    @Override
    public TeacherAssignment update(Long id, Long subjectId, Long groupId, String period, Boolean primary) {
        TeacherAssignment t = teacherAssignmentRepository.findById(id).orElseThrow();
        if (subjectId != null) t.setSubjectId(subjectId);
        if (groupId != null) t.setGroupId(groupId);
        if (period != null) t.setPeriod(period);
        t.setPrimary(primary);
        return teacherAssignmentRepository.save(t);
    }

    @Override
    public void delete(Long id) {
        teacherAssignmentRepository.deleteById(id);
    }

}


