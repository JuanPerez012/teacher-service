package com.teachermicroservice.service;

import com.teachermicroservice.dto.response.TeacherResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TeacherExcelImportService {
    List<TeacherResponseDTO> importFromExcel(MultipartFile file) throws IOException;
}

