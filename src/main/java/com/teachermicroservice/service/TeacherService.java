package com.teachermicroservice.service;

import com.teachermicroservice.dto.request.TeacherRequestDTO;
import com.teachermicroservice.dto.response.PaginatedResponse;
import com.teachermicroservice.dto.response.TeacherResponseDTO;
import com.teachermicroservice.entity.Teacher;
import com.teachermicroservice.exception.TeacherNotFoundException;
import com.teachermicroservice.mapper.TeacherMapper;
import com.teachermicroservice.repository.ITeacherRepository;
import com.teachermicroservice.service.impl.ITeacherService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class TeacherService implements ITeacherService {

    private final ITeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public TeacherService(ITeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    @Override
    public TeacherResponseDTO createTeacher(TeacherRequestDTO request) {
        Teacher teacher = teacherMapper.toEntity(request);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return teacherMapper.toResponseDTO(savedTeacher);
    }

    @Override
    @Transactional(readOnly = true)
    public TeacherResponseDTO getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(TeacherNotFoundException::new);
        return teacherMapper.toResponseDTO(teacher);
    }

    @Override
    @Transactional(readOnly = true)
    public PaginatedResponse<TeacherResponseDTO> getAllPaginated(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Teacher> teacherPage = teacherRepository.findAll(pageable);

        List<TeacherResponseDTO> teacherDTOs = teacherPage.getContent()
                .stream()
                .map(teacherMapper::toResponseDTO)
                .toList();

        return new PaginatedResponse<>(
                teacherDTOs,
                teacherPage.getNumber(),
                teacherPage.getTotalPages(),
                teacherPage.getTotalElements(),
                teacherPage.getSize(),
                teacherPage.hasNext(),
                teacherPage.hasPrevious()
        );
    }

    @Override
    public TeacherResponseDTO updateTeacher(Long id, TeacherRequestDTO request) {
        Teacher existing = teacherRepository.findById(id)
                .orElseThrow(TeacherNotFoundException::new);

        Teacher updatedTeacher = teacherMapper.toEntity(request);
        updatedTeacher.setId(existing.getId());

        Teacher saved = teacherRepository.save(updatedTeacher);
        return teacherMapper.toResponseDTO(saved);
    }

    @Override
    public void deleteTeacher(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new TeacherNotFoundException();
        }
        teacherRepository.deleteById(id);
    }
}