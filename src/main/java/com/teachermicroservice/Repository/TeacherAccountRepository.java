package com.teachermicroservice.Repository;

import com.teachermicroservice.Entity.TeacherAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherAccountRepository extends JpaRepository<TeacherAccount, Long> {
    Optional<TeacherAccount> findByTeacherId(Long teacherId);
    Optional<TeacherAccount> findByUserAccountId(String userAccountId);
}


