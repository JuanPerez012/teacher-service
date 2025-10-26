package com.teachermicroservice.controller;

import com.teachermicroservice.dto.request.TeacherRequestDTO;
import com.teachermicroservice.dto.response.PaginatedResponse;
import com.teachermicroservice.dto.response.TeacherResponseDTO;
import com.teachermicroservice.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TeacherController.class)
class TeacherControllerTest {

    private static final String BASE = "/api/v1/teachers";

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private TeacherService service;

    private static BigDecimal bd(String v) {
        return new BigDecimal(v);
    }

    private static TeacherResponseDTO sample(Long id) {
        return new TeacherResponseDTO(
                id,
                "Ada",
                "Lovelace",
                "ada.lovelace@example.com",
                "FEMENINO",
                "3001234567",
                "Calle 123 #45-67",
                "Ingeniería de Software",
                LocalDate.of(2020, 1, 15),
                bd("8500.00"),
                "Titular",
                "Ciencias de la Computación")
                ;
    }

    @Test
    void getById_ok() throws Exception {
        when(service.getTeacherById(1L)).thenReturn(sample(1L));

        mvc.perform(get(BASE + "/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("Ada"))
                .andExpect(jsonPath("$.lastName").value("Lovelace"))
                .andExpect(jsonPath("$.email").value("ada.lovelace@example.com"));
    }

    @Test
    void create_ok() throws Exception {
        when(service.createTeacher(any(TeacherRequestDTO.class)))
                .thenReturn(sample(3L));

        String body = """
                {
                  "firstName": "Ada",
                  "lastName": "Lovelace",
                  "email": "ada.lovelace@example.com",
                  "birthDate": "1990-12-10",
                  "gender": "FEMENINO",
                  "phone": "3001234567",
                  "address": "Calle 123 #45-67",
                  "specialty": "Ingeniería de Software",
                  "hireDate": "2020-01-15",
                  "salary": 8500.00,
                  "academicRank": "Titular",
                  "department": "Ciencias de la Computación"
                }
                """;

        mvc.perform(post(BASE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.firstName").value("Ada"))
                .andExpect(jsonPath("$.specialty").value("Ingeniería de Software"));
    }

    @Test
    void update_ok() throws Exception {
        when(service.updateTeacher(eq(4L), any(TeacherRequestDTO.class)))
                .thenReturn(new TeacherResponseDTO(
                        4L,
                        "Grace",
                        "Hopper",
                        "grace.hopper@example.com",
                        "FEMENINO",
                        "3017654321",
                        "Av. Principal 10-20",
                        "Compiladores",
                        LocalDate.of(2021, 3, 10),
                        bd("9200.00"),
                        "Asociada",
                        "Sistemas"));

        String body = """
                {
                  "firstName": "Grace",
                  "lastName": "Hopper",
                  "email": "grace.hopper@example.com",
                  "birthDate": "1985-12-09",
                  "gender": "FEMENINO",
                  "phone": "3017654321",
                  "address": "Av. Principal 10-20",
                  "specialty": "Compiladores",
                  "hireDate": "2021-03-10",
                  "salary": 9200.00,
                  "academicRank": "Asociada",
                  "department": "Sistemas"
                }
                """;

        mvc.perform(put(BASE + "/{id}", 4L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(4))
                .andExpect(jsonPath("$.firstName").value("Grace"))
                .andExpect(jsonPath("$.specialty").value("Compiladores"));
    }

    @Test
    void delete_noContent() throws Exception {
        doNothing().when(service).deleteTeacher(5L);

        mvc.perform(delete(BASE + "/{id}", 5L))
                .andExpect(status().isNoContent());
    }

    @Test
    void getAllPaginated_ok() throws Exception {
        PaginatedResponse<TeacherResponseDTO> page = new PaginatedResponse<>(
                List.of(sample(6L)),
                0,
                1,
                1,
                10,
                false,
                false
        );

        when(service.getAllPaginated(0, 10)).thenReturn(page);

        mvc.perform(get(BASE + "/paginated")
                        .param("page", "0")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(6))
                .andExpect(jsonPath("$.content[0].firstName").value("Ada"))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.currentPage").value(0))
                .andExpect(jsonPath("$.pageSize").value(10));
    }
}
