package com.teachermicroservice.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ApiPathsTest {

    @Test
    void teachersPath_ok() {
        assertEquals("/api/v1/teachers", ApiPaths.TEACHERS);
    }

    @Test
    void baseApiPath_ok() {
        assertEquals("/api/v1", ApiPaths.BASE_API);
    }
}
