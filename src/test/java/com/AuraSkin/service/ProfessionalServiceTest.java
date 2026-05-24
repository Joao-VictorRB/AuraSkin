package com.AuraSkin.service;

import com.AuraSkin.entity.Professional;
import com.AuraSkin.repository.ProfessionalRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProfessionalServiceTest {

    private ProfessionalRepository professionalRepository;
    private ProfessionalService professionalService;

    @BeforeEach
    void setup() {
        professionalRepository = Mockito.mock(ProfessionalRepository.class);
        professionalService = new ProfessionalService(professionalRepository);
    }

    @Test
    void shouldSaveProfessional() {

        Professional professional = new Professional();
        professional.setName("Maria");

        when(professionalRepository.save(professional))
                .thenReturn(professional);

        Professional saved =
                professionalService.saveProfessional(professional);

        assertNotNull(saved);
        assertEquals("Maria", saved.getName());
    }

    @Test
    void shouldReturnAllProfessionals() {

        Professional professional = new Professional();
        professional.setName("Maria");

        when(professionalRepository.findAll())
                .thenReturn(List.of(professional));

        List<Professional> professionals =
                professionalService.getAllProfessionals();

        assertEquals(1, professionals.size());
    }

    @Test
    void shouldReturnProfessionalById() {

        Professional professional = new Professional();
        professional.setIdProfessional(1L);

        when(professionalRepository.findById(1L))
                .thenReturn(Optional.of(professional));

        Professional found =
                professionalService.getProfessionalById(1L);

        assertEquals(1L, found.getIdProfessional());
    }

    @Test
    void shouldUpdateProfessional() {

        Professional existing = new Professional();
        existing.setName("Maria");

        Professional updated = new Professional();
        updated.setName("Ana");

        when(professionalRepository.findById(1L))
                .thenReturn(Optional.of(existing));

        when(professionalRepository.save(existing))
                .thenReturn(existing);

        Professional result =
                professionalService.updateProfessional(1L, updated);

        assertEquals("Ana", result.getName());
    }

    @Test
    void shouldThrowExceptionWhenUpdatingProfessionalNotFound() {

        Professional professional = new Professional();

        when(professionalRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> professionalService.updateProfessional(1L, professional));
    }

   @Test
   void shouldThrowExceptionWhenProfessionalNotFound() {

        when(professionalRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,() -> professionalService.getProfessionalById(1L));
 }

    @Test
    void shouldDeleteProfessional() {

        professionalService.deleteProfessional(1L);

        verify(professionalRepository, times(1))
                .deleteById(1L);
    }
}