package com.teachermicroservice.response;

import com.teachermicroservice.dto.response.PaginatedResponse;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaginatedResponseTest {

    @Test
    void fullGetterSetterAndBooleansBranches() {
        List<String> content = new ArrayList<>();
        content.add("A");

        PaginatedResponse<String> p = new PaginatedResponse<>(
                content, 0, 1, 1, 10, true, false
        );

        assertEquals(1, p.getContent().size());
        assertEquals(0, p.getCurrentPage());
        assertEquals(1, p.getTotalPages());
        assertEquals(1L, p.getTotalElements());
        assertEquals(10, p.getPageSize());
        assertTrue(p.isHasNext());
        assertFalse(p.isHasPrevious());

        p.setContent(List.of("X", "Y"));
        p.setCurrentPage(2);
        p.setTotalPages(5);
        p.setTotalElements(7L);
        p.setPageSize(20);
        p.setHasNext(false);
        p.setHasPrevious(true);

        assertEquals(2, p.getContent().size());
        assertEquals(2, p.getCurrentPage());
        assertEquals(5, p.getTotalPages());
        assertEquals(7L, p.getTotalElements());
        assertEquals(20, p.getPageSize());
        assertFalse(p.isHasNext());
        assertTrue(p.isHasPrevious());
    }
}
