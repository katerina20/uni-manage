package com.group.unimanage.controller;

import com.group.unimanage.service.LectorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class LectorSearchControllerTest {

    @Mock
    private LectorService lectorService;

    @InjectMocks
    private LectorSearchController lectorSearchController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(lectorSearchController).build();
    }

    @Test
    void searchLectors_ShouldReturnLectors() throws Exception {
        List<String> lectors = Arrays.asList("Yaroslav Ivanenko", "Sergii Prokopenko", "Yuliya Shevchen");
        given(lectorService.searchLectors("ko")).willReturn(lectors);

        mockMvc.perform(get("/search")
                .param("query", "ko")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0]").value("Yaroslav Ivanenko"))
            .andExpect(jsonPath("$[1]").value("Sergii Prokopenko"));
    }
}