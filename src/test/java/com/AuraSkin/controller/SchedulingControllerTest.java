package com.AuraSkin.controller;

import com.AuraSkin.dto.SchedulingDTO;
import com.AuraSkin.entity.Scheduling;
import com.AuraSkin.enums.SchedulingStatus;
import com.AuraSkin.service.SchedulingService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SchedulingController.class)
public class SchedulingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SchedulingService schedulingService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllSchedulings() throws Exception {

        Scheduling scheduling = new Scheduling();
        scheduling.setId(1L);

        when(schedulingService.getAllSchedulings())
                .thenReturn(List.of(scheduling));

        mockMvc.perform(get("/schedulings"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnSchedulingById() throws Exception {

        Scheduling scheduling = new Scheduling();
        scheduling.setId(1L);

        when(schedulingService.getSchedulingById(1L))
                .thenReturn(scheduling);

        mockMvc.perform(get("/schedulings/1"))
                .andExpect(status().isOk());
    }

    @Test
    @SuppressWarnings("null")
    void shouldCreateScheduling() throws Exception {

        SchedulingDTO dto = new SchedulingDTO(
                LocalDate.now(),
                LocalTime.now(),
                SchedulingStatus.SCHEDULED,
                1L,
                1L,
                1L
        );

        Scheduling scheduling = new Scheduling();

        when(schedulingService.saveScheduling(any(SchedulingDTO.class)))
                .thenReturn(scheduling);

        mockMvc.perform(post("/schedulings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    @SuppressWarnings("null")
    void shouldUpdateScheduling() throws Exception {

        SchedulingDTO dto = new SchedulingDTO(
                LocalDate.now(),
                LocalTime.now(),
                SchedulingStatus.SCHEDULED,
                1L,
                1L,
                1L
        );

        Scheduling scheduling = new Scheduling();

        when(schedulingService.updateScheduling(eq(1L),
                any(SchedulingDTO.class)))
                .thenReturn(scheduling);

        mockMvc.perform(put("/schedulings/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeleteScheduling() throws Exception {

        mockMvc.perform(delete("/schedulings/1"))
                .andExpect(status().isOk());
    }
}