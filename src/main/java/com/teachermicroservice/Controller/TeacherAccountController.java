package com.teachermicroservice.Controller;

import com.teachermicroservice.Entity.Teacher;
import com.teachermicroservice.Entity.TeacherAccount;
import com.teachermicroservice.Service.TeacherAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/teachers/accounts")
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS})
public class TeacherAccountController {

    private final TeacherAccountService service;

    public TeacherAccountController(TeacherAccountService service) {
        this.service = service;
    }

    public record CreateTeacherAccountRequest(Teacher teacher, String userAccountId, String role, Boolean enabled) {}
    public record UpdateTeacherAccountRequest(String role, Boolean enabled) {}

    @PostMapping
    public ResponseEntity<TeacherAccount> create(@RequestBody CreateTeacherAccountRequest req) {
        return ResponseEntity.status(201).body(service.create(req.teacher(), req.userAccountId(), req.role(), req.enabled()));
    }


    @GetMapping("/{id}")
    public ResponseEntity<TeacherAccount> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<TeacherAccount>> list() {
        return ResponseEntity.ok(service.listAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherAccount> update(@PathVariable Long id, @RequestBody UpdateTeacherAccountRequest req) {
        return ResponseEntity.ok(service.update(id, req.role(), req.enabled()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}


