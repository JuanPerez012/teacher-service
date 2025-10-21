package com.teachermicroservice;

import com.teachermicroservice.config.CorsConfig;
import com.teachermicroservice.controller.TeacherController;
import com.teachermicroservice.exception.handler.GlobalExceptionHandler;
import com.teachermicroservice.mapper.TeacherMapper;
import com.teachermicroservice.repository.ITeacherRepository;
import com.teachermicroservice.service.TeacherService;
import com.teachermicroservice.service.impl.ITeacherService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.context.ApplicationContext;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeachermicroserviceApplicationTests {

    @Autowired
    private ApplicationContext context;

    @MockitoBean
    private ITeacherRepository teacherRepository;

    @Test
    void contextLoads() {
        assertNotNull(context);
    }

    @DisplayName("Todos los beans clave deben estar presentes en el contexto")
    @ParameterizedTest(name = "Bean presente por tipo: {0}")
    @MethodSource("beanTypes")
    void beansShouldBePresentByType(Class<?> type) {
        Object bean = assertDoesNotThrow(
                () -> context.getBean(type),
                () -> "No se pudo resolver bean de tipo: " + type.getName()
        );
        assertNotNull(bean, "El bean no debería ser nulo");
        assertTrue(type.isInstance(bean) || type.isAssignableFrom(bean.getClass()),
                "El bean obtenido no es del tipo esperado (puede ser un proxy)");
    }

    static Stream<Class<?>> beanTypes() {
        return Stream.of(
                TeacherController.class,
                ITeacherService.class,
                TeacherService.class,
                TeacherMapper.class,
                ITeacherRepository.class,
                GlobalExceptionHandler.class,
                CorsConfig.class
        );

    }
}
