package com.teachermicroservice.controller;

import com.teachermicroservice.dto.request.TeacherRequestDTO;
import com.teachermicroservice.dto.response.PaginatedResponse;
import com.teachermicroservice.dto.response.TeacherResponseDTO;
import com.teachermicroservice.service.TeacherService;
import com.teachermicroservice.service.TeacherExcelImportService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.teachermicroservice.utils.ApiPaths.TEACHERS;

@RestController
@RequestMapping(TEACHERS)
public class TeacherController {

    private final TeacherService service;
    private final TeacherExcelImportService excelImportService;

    public TeacherController(TeacherService service, TeacherExcelImportService excelImportService) {
        this.service = service;
        this.excelImportService = excelImportService;
    }

    @PostMapping
    public ResponseEntity<TeacherResponseDTO> createTeacher(@Valid @RequestBody TeacherRequestDTO request) {
        TeacherResponseDTO created = service.createTeacher(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping(value = "/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<TeacherResponseDTO>> importFromExcel(@RequestParam("file") MultipartFile file) throws IOException {
        List<TeacherResponseDTO> created = excelImportService.importFromExcel(file);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> getTeacherById(@PathVariable Long id) {
        TeacherResponseDTO teacher = service.getTeacherById(id);
        return ResponseEntity.ok(teacher);
    }

    @GetMapping("/paginated")
    public ResponseEntity<PaginatedResponse<TeacherResponseDTO>> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        PaginatedResponse<TeacherResponseDTO> response = service.getAllPaginated(page, size);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponseDTO> updateTeacher(
            @PathVariable Long id,
            @Valid @RequestBody TeacherRequestDTO request) {

        TeacherResponseDTO updated = service.updateTeacher(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) {
        service.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}