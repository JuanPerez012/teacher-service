package com.teachermicroservice.handler;

import com.teachermicroservice.exception.TeacherNotFoundException;
import com.teachermicroservice.exception.handler.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleTeacherNotFound_returns404Body() {
        ResponseEntity<Map<String, Object>> resp =
                handler.handleStudentNotFound(new TeacherNotFoundException());

        assertEquals(404, resp.getStatusCode().value());
        assertTrue(resp.getBody().get("message").toString().contains("Docente no encontrado"));
        assertTrue(resp.getBody().containsKey("timestamp"));
        assertTrue(resp.getBody().containsKey("status"));
        assertTrue(resp.getBody().containsKey("error"));
    }

    @Test
    void handleGeneric_returns500Body() {
        ResponseEntity<Map<String, Object>> resp =
                handler.handleGeneric(new RuntimeException("boom"));

        assertEquals(500, resp.getStatusCode().value());
        assertTrue(resp.getBody().get("message").toString().contains("boom"));
        assertTrue(resp.getBody().containsKey("timestamp"));
        assertTrue(resp.getBody().containsKey("status"));
        assertTrue(resp.getBody().containsKey("error"));
    }
}
