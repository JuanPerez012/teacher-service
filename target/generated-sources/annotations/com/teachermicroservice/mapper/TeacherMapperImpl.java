package com.teachermicroservice.mapper;

import com.teachermicroservice.dto.request.TeacherRequestDTO;
import com.teachermicroservice.dto.response.TeacherResponseDTO;
import com.teachermicroservice.entity.Teacher;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-28T10:22:50-0500",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.4 (Oracle Corporation)"
)
@Component
public class TeacherMapperImpl implements TeacherMapper {

    @Override
    public Teacher toEntity(TeacherRequestDTO teacherRequestDTO) {
        if ( teacherRequestDTO == null ) {
            return null;
        }

        Teacher teacher = new Teacher();

        teacher.setSpecialty( teacherRequestDTO.specialty() );
        teacher.setHireDate( teacherRequestDTO.hireDate() );
        teacher.setSalary( teacherRequestDTO.salary() );
        teacher.setAcademicRank( teacherRequestDTO.academicRank() );
        teacher.setDepartment( teacherRequestDTO.department() );

        return teacher;
    }

    @Override
    public TeacherResponseDTO toResponseDTO(Teacher teacher) {
        if ( teacher == null ) {
            return null;
        }

        Long id = null;
        String specialty = null;
        LocalDate hireDate = null;
        BigDecimal salary = null;
        String academicRank = null;
        String department = null;

        id = teacher.getId();
        specialty = teacher.getSpecialty();
        hireDate = teacher.getHireDate();
        salary = teacher.getSalary();
        academicRank = teacher.getAcademicRank();
        department = teacher.getDepartment();

        TeacherResponseDTO teacherResponseDTO = new TeacherResponseDTO( id, specialty, hireDate, salary, academicRank, department );

        return teacherResponseDTO;
    }
}
