package com.group.unimanage.controller;

import com.group.unimanage.model.Department;
import com.group.unimanage.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class DepartmentControllerTest {

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
    }

    @Test
    void getDepartments_ShouldReturnAllDepartments() throws Exception {
        List<Department> departments = Collections.singletonList(new Department("1", "Math", null));
        given(departmentService.getDepartments()).willReturn(departments);

        mockMvc.perform(get("/department")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value("1"))
            .andExpect(jsonPath("$[0].name").value("Math"));
    }

    @Test
    void getDepartmentStatistics_ShouldReturnStatistics() throws Exception {
        Map<String, Integer> statistics = new HashMap<>();
        statistics.put("ASSISTANT", 2);
        statistics.put("ASSOCIATE_PROFESSOR", 1);
        statistics.put("PROFESSOR", 1);
        given(departmentService.getDepartmentStatistics("1")).willReturn(statistics);

        mockMvc.perform(get("/department/1/statistics")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.ASSISTANT").value(2))
            .andExpect(jsonPath("$.ASSOCIATE_PROFESSOR").value(1))
            .andExpect(jsonPath("$.PROFESSOR").value(1));
    }
}