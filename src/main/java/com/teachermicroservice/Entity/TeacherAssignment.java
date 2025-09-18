package com.teachermicroservice.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.*;

@Entity
@Table(name = "teacher_assignments",
        uniqueConstraints = @UniqueConstraint(name = "uk_assignment", columnNames = {"teacher_id","subject_id","group_id","period"}))
public class TeacherAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @NotNull
    @Column(name = "subject_id", nullable = false)
    private Long subjectId;

    @NotNull
    @Column(name = "group_id", nullable = false)
    private Long groupId;

    @NotBlank
    @Size(max = 20)
    @Column(nullable = false, length = 20)
    private String period;

    @NotNull
    @Column(name = "is_primary", nullable = false)
    private Boolean primary;

    @NotNull
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
        if (this.primary == null) this.primary = Boolean.FALSE;
    }

    @PreUpdate
    public void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Teacher getTeacher() { return teacher; }
    public Long getSubjectId() { return subjectId; }
    public Long getGroupId() { return groupId; }
    public String getPeriod() { return period; }
    public Boolean getPrimary() { return primary; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void setId(Long id) { this.id = id; }
    public void setTeacher(Teacher teacher) { this.teacher = teacher; }
    public void setSubjectId(Long subjectId) { this.subjectId = subjectId; }
    public void setGroupId(Long groupId) { this.groupId = groupId; }
    public void setPeriod(String period) { this.period = period; }
    public void setPrimary(Boolean primary) { this.primary = primary; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}


