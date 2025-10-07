package com.teachermicroservice.Controller;

import com.teachermicroservice.Entity.Teacher;
import com.teachermicroservice.Entity.TeacherAssignment;
import com.teachermicroservice.Repository.TeacherAccountRepository;
import com.teachermicroservice.Service.TeacherAssignmentService;
import com.teachermicroservice.Service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/teachers/assignments")
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class TeacherAssignmentController {

    private final TeacherAssignmentService service;

    public TeacherAssignmentController(TeacherAssignmentService service) {
        this.service = service;
    }

    public record CreateTeacherAssignmentRequest(Teacher teacher, Long subjectId, Long groupId, String period, Boolean primary) {}
    public record UpdateTeacherAssignmentRequest(Long subjectId, Long groupId, String period, Boolean primary) {}

    @PostMapping
    public ResponseEntity<TeacherAssignment> create(@RequestBody CreateTeacherAssignmentRequest req) {
        return ResponseEntity.status(201).body(service.create(req.teacher(), req.subjectId(), req.groupId(), req.period(), req.primary()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherAssignment> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<TeacherAssignment>> list(@PathVariable Long teacherId) {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/by_teacher/{id}")
    public ResponseEntity<List<TeacherAssignment>> filterByTeacher(@PathVariable Long teacherId) {
        return ResponseEntity.ok(service.filterByTeacher(teacherId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherAssignment> update(@PathVariable Long id, @RequestBody UpdateTeacherAssignmentRequest req) {
        return ResponseEntity.ok(service.update(id, req.subjectId(), req.groupId(), req.period(), req.primary()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


