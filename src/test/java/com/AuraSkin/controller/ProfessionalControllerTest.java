package com.AuraSkin.controller;

import com.AuraSkin.dto.ProfessionalDTO;
import com.AuraSkin.entity.Professional;
import com.AuraSkin.service.ProfessionalService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProfessionalController.class)
public class ProfessionalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProfessionalService professionalService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllProfessionals() throws Exception {

        Professional professional = new Professional();
        professional.setIdProfessional(1L);
        professional.setName("Maria");

        when(professionalService.getAllProfessionals())
                .thenReturn(List.of(professional));

        mockMvc.perform(get("/professionals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name")
                .value("Maria"));
    }

    @Test
    void shouldReturnProfessionalById() throws Exception {

        Professional professional = new Professional();
        professional.setIdProfessional(1L);
        professional.setName("Maria");

        when(professionalService.getProfessionalById(1L))
                .thenReturn(professional);

        mockMvc.perform(get("/professionals/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")
                .value("Maria"));
    }

    @Test
    @SuppressWarnings("null")
        void shouldCreateProfessional() throws Exception {

        ProfessionalDTO dto = new ProfessionalDTO();
        dto.setName("Maria");
        dto.setSpecialty("Dermatologista");

        Professional professional = new Professional();
        professional.setName(dto.getName());
        professional.setSpecialty(dto.getSpecialty());

        when(professionalService.saveProfessional(any(Professional.class)))
                .thenReturn(professional);

        mockMvc.perform(post("/professionals")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    @SuppressWarnings("null")
    void shouldUpdateProfessional() throws Exception {

        Professional professional = new Professional();
        professional.setName("Maria");

        when(professionalService.updateProfessional(eq(1L),
                any(Professional.class)))
                .thenReturn(professional);

        mockMvc.perform(put("/professionals/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(professional)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteProfessional() throws Exception {

        mockMvc.perform(delete("/professionals/1"))
                .andExpect(status().isOk());
    }
}