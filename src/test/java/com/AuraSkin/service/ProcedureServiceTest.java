package com.AuraSkin.service;

import com.AuraSkin.entity.Procedure;
import com.AuraSkin.repository.ProcedureRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProcedureServiceTest {

    private ProcedureRepository procedureRepository;
    private ProcedureService procedureService;

    @BeforeEach
    void setup() {
        procedureRepository = Mockito.mock(ProcedureRepository.class);
        procedureService = new ProcedureService(procedureRepository);
    }

    @Test
    void shouldSaveProcedure() {

        Procedure procedure = new Procedure();
        procedure.setName("Limpeza");

        when(procedureRepository.save(procedure))
                .thenReturn(procedure);

        Procedure saved =
                procedureService.saveProcedure(procedure);

        assertEquals("Limpeza", saved.getName());
    }

    @Test
    void shouldReturnAllProcedures() {

        Procedure procedure = new Procedure();

        when(procedureRepository.findAll())
                .thenReturn(List.of(procedure));

        List<Procedure> procedures =
                procedureService.getAllProcedures();

        assertEquals(1, procedures.size());
    }

    @Test
    void shouldReturnProcedureById() {

        Procedure procedure = new Procedure();
        procedure.setIdProcedure(1L);

        when(procedureRepository.findById(1L))
                .thenReturn(Optional.of(procedure));

        Procedure found =
                procedureService.getProcedureById(1L);

        assertEquals(1L, found.getIdProcedure());
    }

    @Test
    void shouldThrowExceptionWhenProcedureNotFound() {

        when(procedureRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> procedureService.getProcedureById(1L));
    }

    @Test
    void shouldDeleteProcedure() {

        procedureService.deleteProcedure(1L);

        verify(procedureRepository, times(1))
                .deleteById(1L);
    }
}