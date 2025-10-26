package com.teachermicroservice.service;

import com.teachermicroservice.dto.request.TeacherRequestDTO;
import com.teachermicroservice.dto.response.PaginatedResponse;
import com.teachermicroservice.dto.response.TeacherResponseDTO;
import com.teachermicroservice.entity.Teacher;
import com.teachermicroservice.exception.TeacherNotFoundException;
import com.teachermicroservice.mapper.TeacherMapper;
import com.teachermicroservice.repository.ITeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {

    @Mock private ITeacherRepository repository;
    @Mock private TeacherMapper mapper;

    @InjectMocks private TeacherService service;

    private TeacherRequestDTO req;
    private Teacher entity;
    private TeacherResponseDTO resp;

    private static BigDecimal bd(String v) { return new BigDecimal(v); }

    @BeforeEach
    void setUp() {
        req = new TeacherRequestDTO(
                "Ingeniería de Software",
                LocalDate.of(2020, 1, 15),
                bd("8500.00"),
                "Titular",
                "Ciencias de la Computación"
        );

        entity = new Teacher();
        entity.setId(1L);
        entity.setSpecialty("Ingeniería de Software");
        entity.setHireDate(LocalDate.of(2020, 1, 15));
        entity.setSalary(bd("8500.00"));
        entity.setAcademicRank("Titular");
        entity.setDepartment("Ciencias de la Computación");

        resp = new TeacherResponseDTO(
                1L,
                "Ingeniería de Software",
                LocalDate.of(2020, 1, 15),
                bd("8500.00"),
                "Titular",
                "Ciencias de la Computación"
        );
    }

    @Test
    void create_ok() {
        when(mapper.toEntity(req)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toResponseDTO(entity)).thenReturn(resp);

        TeacherResponseDTO out = service.createTeacher(req);

        assertNotNull(out);
        verify(mapper).toEntity(req);
        verify(repository).save(entity);
        verify(mapper).toResponseDTO(entity);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void getById_ok() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));
        when(mapper.toResponseDTO(entity)).thenReturn(resp);

        TeacherResponseDTO out = service.getTeacherById(1L);

        assertEquals(1L, out.id());
        verify(repository).findById(1L);
        verify(mapper).toResponseDTO(entity);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void getById_notFound_throws() {
        when(repository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(TeacherNotFoundException.class, () -> service.getTeacherById(999L));
        verify(repository).findById(999L);
        verifyNoInteractions(mapper);
    }

    @Test
    void getAllPaginated_firstPage_noPrevNoNext() {
        PageRequest pr = PageRequest.of(0, 10);
        Page<Teacher> page = new PageImpl<>(List.of(entity), pr, 1);
        when(repository.findAll(pr)).thenReturn(page);
        when(mapper.toResponseDTO(entity)).thenReturn(resp);

        PaginatedResponse<TeacherResponseDTO> out = service.getAllPaginated(0, 10);

        assertEquals(1, out.getTotalElements());
        assertEquals(1, out.getTotalPages());
        assertEquals(0, out.getCurrentPage());
        assertEquals(10, out.getPageSize());
        assertFalse(out.isHasNext());
        assertFalse(out.isHasPrevious());

        verify(repository).findAll(pr);
        verify(mapper).toResponseDTO(entity);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void getAllPaginated_middlePage_hasPrevAndNext() {
        PageRequest pr = PageRequest.of(1, 2);
        Page<Teacher> page = new PageImpl<>(List.of(entity, entity), pr, 5);
        when(repository.findAll(pr)).thenReturn(page);
        when(mapper.toResponseDTO(entity)).thenReturn(resp);

        PaginatedResponse<TeacherResponseDTO> out = service.getAllPaginated(1, 2);

        assertEquals(5, out.getTotalElements());
        assertEquals(3, out.getTotalPages());
        assertTrue(out.isHasPrevious());
        assertTrue(out.isHasNext());

        verify(repository).findAll(pr);
        verify(mapper, times(2)).toResponseDTO(entity);
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void update_ok_setsSameId() {
        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        Teacher mapped = new Teacher();
        mapped.setHireDate(LocalDate.of(2021, 3, 10));
        mapped.setSalary(bd("9200.00"));
        mapped.setSpecialty("Compiladores");
        mapped.setAcademicRank("Asociada");
        mapped.setDepartment("Sistemas");

        Teacher saved = new Teacher();
        saved.setId(1L);
        saved.setHireDate(LocalDate.of(2021, 3, 10));
        saved.setSalary(bd("9200.00"));
        saved.setSpecialty("Compiladores");
        saved.setAcademicRank("Asociada");
        saved.setDepartment("Sistemas");

        TeacherResponseDTO updatedResp = new TeacherResponseDTO(
                1L, "Compiladores", LocalDate.of(2021, 3, 10),
                bd("9200.00"), "Asociada", "Sistemas"
        );

        when(mapper.toEntity(req)).thenReturn(mapped);
        when(repository.save(any(Teacher.class))).thenReturn(saved);
        when(mapper.toResponseDTO(saved)).thenReturn(updatedResp);

        TeacherResponseDTO out = service.updateTeacher(1L, req);

        assertEquals(1L, out.id());

        ArgumentCaptor<Teacher> cap = ArgumentCaptor.forClass(Teacher.class);
        verify(repository).findById(1L);
        verify(mapper).toEntity(req);
        verify(repository).save(cap.capture());
        verify(mapper).toResponseDTO(saved);

        assertEquals(1L, cap.getValue().getId());
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void update_notFound_throws() {
        when(repository.findById(123L)).thenReturn(Optional.empty());

        assertThrows(TeacherNotFoundException.class, () -> service.updateTeacher(123L, req));
        verify(repository).findById(123L);
        verify(repository, never()).save(any());
        verifyNoInteractions(mapper);
    }

    @Test
    void delete_ok() {
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        service.deleteTeacher(1L);

        verify(repository).existsById(1L);
        verify(repository).deleteById(1L);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void delete_notFound_throws() {
        when(repository.existsById(999L)).thenReturn(false);

        assertThrows(TeacherNotFoundException.class, () -> service.deleteTeacher(999L));
        verify(repository).existsById(999L);
        verify(repository, never()).deleteById(anyLong());
    }
}
