package com.teachermicroservice.mapper;

import com.teachermicroservice.dto.request.TeacherRequestDTO;
import com.teachermicroservice.dto.response.TeacherResponseDTO;
import com.teachermicroservice.entity.Teacher;
import com.teachermicroservice.enums.Gender;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-18T21:31:02-0500",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class TeacherMapperImpl implements TeacherMapper {

    @Override
    public Teacher toEntity(TeacherRequestDTO teacherRequestDTO) {
        if ( teacherRequestDTO == null ) {
            return null;
        }

        Teacher teacher = new Teacher();

        teacher.setFirstName( teacherRequestDTO.firstName() );
        teacher.setLastName( teacherRequestDTO.lastName() );
        teacher.setEmail( teacherRequestDTO.email() );
        teacher.setBirthDate( teacherRequestDTO.birthDate() );
        if ( teacherRequestDTO.gender() != null ) {
            teacher.setGender( Enum.valueOf( Gender.class, teacherRequestDTO.gender() ) );
        }
        teacher.setPhone( teacherRequestDTO.phone() );
        teacher.setAddress( teacherRequestDTO.address() );
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
        String firstName = null;
        String lastName = null;
        String email = null;
        String gender = null;
        String phone = null;
        String address = null;
        String specialty = null;
        LocalDate hireDate = null;
        BigDecimal salary = null;
        String academicRank = null;
        String department = null;

        id = teacher.getId();
        firstName = teacher.getFirstName();
        lastName = teacher.getLastName();
        email = teacher.getEmail();
        if ( teacher.getGender() != null ) {
            gender = teacher.getGender().name();
        }
        phone = teacher.getPhone();
        address = teacher.getAddress();
        specialty = teacher.getSpecialty();
        hireDate = teacher.getHireDate();
        salary = teacher.getSalary();
        academicRank = teacher.getAcademicRank();
        department = teacher.getDepartment();

        TeacherResponseDTO teacherResponseDTO = new TeacherResponseDTO( id, firstName, lastName, email, gender, phone, address, specialty, hireDate, salary, academicRank, department );

        return teacherResponseDTO;
    }
}
