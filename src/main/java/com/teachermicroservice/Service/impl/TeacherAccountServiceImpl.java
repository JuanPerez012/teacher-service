package com.teachermicroservice.Service.impl;

import com.teachermicroservice.Entity.Teacher;
import com.teachermicroservice.Entity.TeacherAccount;
import com.teachermicroservice.Repository.TeacherAccountRepository;
import com.teachermicroservice.Service.TeacherAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class TeacherAccountServiceImpl implements TeacherAccountService {

    private final TeacherAccountRepository teacherAccountRepository;

    public TeacherAccountServiceImpl(TeacherAccountRepository teacherAccountRepository) {
        this.teacherAccountRepository = teacherAccountRepository;
    }

    @Override
    public TeacherAccount create(Teacher teacher, String userAccountId, String role, Boolean enabled) {
        TeacherAccount t = new TeacherAccount();
        t.setTeacher(teacher);
        t.setUserAccountId(userAccountId);
        t.setRole(role);
        t.setEnabled(enabled);
        return teacherAccountRepository.save(t);
    }

    @Override
    @Transactional(readOnly = true)
    public TeacherAccount getById(Long id) {
        return teacherAccountRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeacherAccount> listAll() {
        return teacherAccountRepository.findAll();
    }

    @Override
    public TeacherAccount update(Long id, String role, Boolean enabled) {
        TeacherAccount t = teacherAccountRepository.findById(id).orElseThrow();
        if (role != null) t.setRole(role);
        t.setEnabled(enabled);
        return teacherAccountRepository.save(t);
    }

    @Override
    public void delete(Long id) {
        teacherAccountRepository.deleteById(id);
    }

}


