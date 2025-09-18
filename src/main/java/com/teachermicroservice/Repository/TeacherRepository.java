package com.teachermicroservice.Repository;

import com.teachermicroservice.Entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsByPersonId(Long personId);
    Optional<Teacher> findByPersonId(Long personId);
    List<Teacher> findBySpecialtyContainingIgnoreCase(String specialty);
    List<Teacher> findByStatus(String status);

    @Query("select t from Teacher t where lower(t.specialty) like lower(concat('%', :q, '%')) or t.status = :q")
    List<Teacher> search(@Param("q") String q);
}


