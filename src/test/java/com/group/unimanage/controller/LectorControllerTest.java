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

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LectorControllerTest {

    @Mock
    private LectorService lectorService;

    @InjectMocks
    private LectorController lectorController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(lectorController).build();
    }

    @Test
    void promoteLector_ShouldReturnOkWhenPromoted() throws Exception {
        given(lectorService.promoteLector("1")).willReturn(true);

        mockMvc.perform(put("/lector/1/promote")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void promoteLector_ShouldReturnBadRequestWhenNotPromoted() throws Exception {
        given(lectorService.promoteLector("1")).willReturn(false);

        mockMvc.perform(put("/lector/1/promote")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }

    @Test
    void changeLectorName_ShouldReturnOkWhenChanged() throws Exception {
        given(lectorService.changeLectorName("1", "Yaroslav Ivanen")).willReturn(true);

        mockMvc.perform(patch("/lector/1/change-name")
                .param("newName", "Yaroslav Ivanen")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    void changeLectorName_ShouldReturnBadRequestWhenNotChanged() throws Exception {
        given(lectorService.changeLectorName("1", "Yaroslav Ivanen")).willReturn(false);

        mockMvc.perform(patch("/lector/1/change-name")
                .param("newName", "Yaroslav Ivanen")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest());
    }
}