package com.teachermicroservice.mapper;

import com.teachermicroservice.dto.request.TeacherRequestDTO;
import com.teachermicroservice.dto.response.TeacherResponseDTO;
import com.teachermicroservice.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TeacherMapper {
    Teacher toEntity(TeacherRequestDTO teacherRequestDTO);
    TeacherResponseDTO toResponseDTO(Teacher teacher);
}