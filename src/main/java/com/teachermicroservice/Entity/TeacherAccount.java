package com.teachermicroservice.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.*;

@Entity
@Table(name = "teacher_accounts",
        uniqueConstraints = @UniqueConstraint(name = "uk_teacher_account_teacher", columnNames = "teacher_id"))
public class TeacherAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @NotNull
    @Column(name = "user_account_id", nullable = false)
    private String userAccountId;

    @NotBlank
    @Size(max = 50)
    @Column(nullable = false, length = 50)
    private String role;

    public Long getId() { return id; }
    public Teacher getTeacher() { return teacher; }
    public String getUserAccountId() { return userAccountId; }
    public String getRole() { return role; }

    public void setId(Long id) { this.id = id; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }
    public void setUserAccountId(String userAccountId) { this.userAccountId = userAccountId; }
    public void setRole(String role) { this.role = role; }

}


