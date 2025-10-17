package com.teachermicroservice.service.impl;

import com.teachermicroservice.dto.request.TeacherRequestDTO;
import com.teachermicroservice.dto.response.PaginatedResponse;
import com.teachermicroservice.dto.response.TeacherResponseDTO;

public interface ITeacherService {
    TeacherResponseDTO createTeacher(TeacherRequestDTO request);
    TeacherResponseDTO getTeacherById(Long id);
    PaginatedResponse<TeacherResponseDTO> getAllPaginated(int page, int size);
    TeacherResponseDTO updateTeacher(Long id, TeacherRequestDTO request);
    void deleteTeacher(Long id);
}