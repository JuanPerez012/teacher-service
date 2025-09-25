package com.teachermicroservice.Service;

import com.teachermicroservice.Entity.Teacher;
import com.teachermicroservice.Entity.TeacherAccount;

import java.time.LocalDateTime;
import java.util.List;

public interface TeacherAccountService {
    TeacherAccount create(Teacher teacher, String userAccountId, String role, Boolean enabled);
    TeacherAccount getById(Long id);
    List<TeacherAccount> listAll();
    TeacherAccount update(Long id, String role, Boolean enabled);
    void delete(Long id);
}


