package com.AuraSkin.controller;

import com.AuraSkin.dto.ProcedureDTO;
import com.AuraSkin.entity.Procedure;
import com.AuraSkin.service.ProcedureService;
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

@WebMvcTest(ProcedureController.class)
public class ProcedureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProcedureService procedureService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllProcedures() throws Exception {

        Procedure procedure = new Procedure();
        procedure.setIdProcedure(1L);
        procedure.setName("Limpeza");

        when(procedureService.getAllProcedures())
                .thenReturn(List.of(procedure));

        mockMvc.perform(get("/procedures"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name")
                .value("Limpeza"));
    }

    @Test
    void shouldReturnProcedureById() throws Exception {

        Procedure procedure = new Procedure();
        procedure.setIdProcedure(1L);
        procedure.setName("Limpeza");

        when(procedureService.getProcedureById(1L))
                .thenReturn(procedure);

        mockMvc.perform(get("/procedures/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")
                .value("Limpeza"));
    }

    @Test
    @SuppressWarnings("null")
    void shouldCreateProcedure() throws Exception {

        ProcedureDTO dto = new ProcedureDTO();
        dto.setName("Limpeza");
        dto.setDescription("Limpeza facial");
        dto.setPrice(150f);
        dto.setDurationMin(60);

        Procedure procedure = new Procedure();
        procedure.setName(dto.getName());
        procedure.setDescription(dto.getDescription());
        procedure.setPrice(dto.getPrice());
        procedure.setDurationMin(dto.getDurationMin());

        when(procedureService.saveProcedure(any(Procedure.class)))
                .thenReturn(procedure);

        mockMvc.perform(post("/procedures")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    @SuppressWarnings("null")
    void shouldUpdateProcedure() throws Exception {

        Procedure procedure = new Procedure();
        procedure.setName("Limpeza");

        when(procedureService.updateProcedure(eq(1L),
                any(Procedure.class)))
                .thenReturn(procedure);

        mockMvc.perform(put("/procedures/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(procedure)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteProcedure() throws Exception {

        mockMvc.perform(delete("/procedures/1"))
                .andExpect(status().isOk());
    }
}