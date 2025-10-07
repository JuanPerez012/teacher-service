package com.teachermicroservice.Service.impl;

import com.teachermicroservice.Entity.Teacher;
import com.teachermicroservice.Repository.TeacherAssignmentRepository;
import com.teachermicroservice.Repository.TeacherRepository;
import com.teachermicroservice.Service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherAssignmentRepository assignmentRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherAssignmentRepository assignmentRepository) {
        this.teacherRepository = teacherRepository;
        this.assignmentRepository = assignmentRepository;
    }

    @Override
    public Teacher create(Long personId, String specialty, String status, LocalDate hireDate) {
        if (teacherRepository.existsByPersonId(personId)) {
            throw new IllegalArgumentException("Teacher already exists for person_id " + personId);
        }
        Teacher t = new Teacher();
        t.setPersonId(personId);
        t.setSpecialty(specialty);
        t.setHireDate(hireDate);
        return teacherRepository.save(t);
    }

    @Override
    @Transactional(readOnly = true)
    public Teacher getById(Long id) {
        return teacherRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Teacher> listAll() {
        return teacherRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Teacher> filterBySpecialty(String specialty) {
        return teacherRepository.findBySpecialtyContainingIgnoreCase(specialty);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Teacher> filterByStatus(String status) {
        return teacherRepository.findByStatus(status);
    }

    @Override
    public Teacher update(Long id, String specialty, String status, LocalDate hireDate) {
        Teacher t = teacherRepository.findById(id).orElseThrow();
        if (specialty != null) t.setSpecialty(specialty);
        t.setHireDate(hireDate);
        return teacherRepository.save(t);
    }

    @Override
    public void delete(Long id) {
        // Restrict delete if active assignments exist
        boolean hasAssignments = !assignmentRepository.findByTeacherId(id).isEmpty();
        if (hasAssignments) {
            throw new IllegalStateException("Cannot delete teacher with active assignments");
        }
        teacherRepository.deleteById(id);
    }
}


