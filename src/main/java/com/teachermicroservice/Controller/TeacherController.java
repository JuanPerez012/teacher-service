package com.teachermicroservice.Controller;

import com.teachermicroservice.Entity.Teacher;
import com.teachermicroservice.Entity.TeacherAssignment;
import com.teachermicroservice.Service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/teachers")
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class TeacherController {

    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    public record CreateTeacherRequest(Long personId, String specialty, String status, LocalDate hireDate) {}
    public record UpdateTeacherRequest(String specialty, String status, LocalDate hireDate) {}
    public record AssignmentRequest(Long subjectId, Long groupId, String period, Boolean primary) {}

    @PostMapping
    public ResponseEntity<Teacher> create(@RequestBody CreateTeacherRequest req) {
        return ResponseEntity.status(201).body(service.create(req.personId(), req.specialty(), req.status(), req.hireDate()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> list(@RequestParam(required = false) String specialty,
                                              @RequestParam(required = false) String status) {
        if (specialty != null) return ResponseEntity.ok(service.filterBySpecialty(specialty));
        if (status != null) return ResponseEntity.ok(service.filterByStatus(status));
        return ResponseEntity.ok(service.listAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> update(@PathVariable Long id, @RequestBody UpdateTeacherRequest req) {
        return ResponseEntity.ok(service.update(id, req.specialty(), req.status(), req.hireDate()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


