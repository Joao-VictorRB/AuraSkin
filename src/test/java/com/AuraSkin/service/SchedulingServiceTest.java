package com.AuraSkin.service;

import com.AuraSkin.dto.SchedulingDTO;
import com.AuraSkin.entity.Client;
import com.AuraSkin.entity.Procedure;
import com.AuraSkin.entity.Professional;
import com.AuraSkin.entity.Scheduling;
import com.AuraSkin.enums.SchedulingStatus;
import com.AuraSkin.repository.ClientRepository;
import com.AuraSkin.repository.ProcedureRepository;
import com.AuraSkin.repository.ProfessionalRepository;
import com.AuraSkin.repository.SchedulingRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SchedulingServiceTest {

    private SchedulingRepository schedulingRepository;
    private ClientRepository clientRepository;
    private ProfessionalRepository professionalRepository;
    private ProcedureRepository procedureRepository;

    private SchedulingService schedulingService;

    @BeforeEach
    void setup() {

        schedulingRepository = Mockito.mock(SchedulingRepository.class);
        clientRepository = Mockito.mock(ClientRepository.class);
        professionalRepository = Mockito.mock(ProfessionalRepository.class);
        procedureRepository = Mockito.mock(ProcedureRepository.class);

        schedulingService = new SchedulingService(
                schedulingRepository,
                clientRepository,
                professionalRepository,
                procedureRepository
        );
    }

    @Test
    @SuppressWarnings("null")
    void shouldSaveScheduling() {

        SchedulingDTO dto = new SchedulingDTO(
                LocalDate.now(),
                LocalTime.now(),
                SchedulingStatus.SCHEDULED,
                1L,
                1L,
                1L
        );

        Client client = new Client();
        Professional professional = new Professional();
        Procedure procedure = new Procedure();

        when(clientRepository.findById(1L))
                .thenReturn(Optional.of(client));

        when(professionalRepository.findById(1L))
                .thenReturn(Optional.of(professional));

        when(procedureRepository.findById(1L))
                .thenReturn(Optional.of(procedure));

        when(schedulingRepository.save(any(Scheduling.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Scheduling saved =
                schedulingService.saveScheduling(dto);

        assertNotNull(saved);
        assertEquals(SchedulingStatus.SCHEDULED,
                saved.getStatus());
    }

    @Test
    void shouldReturnAllSchedulings() {

        Scheduling scheduling = new Scheduling();

        when(schedulingRepository.findAll())
                .thenReturn(List.of(scheduling));

        List<Scheduling> schedulings =
                schedulingService.getAllSchedulings();

        assertEquals(1, schedulings.size());
    }

    @Test
    void shouldReturnSchedulingById() {

        Scheduling scheduling = new Scheduling();
        scheduling.setId(1L);

        when(schedulingRepository.findById(1L))
                .thenReturn(Optional.of(scheduling));

        Scheduling found =
                schedulingService.getSchedulingById(1L);

        assertEquals(1L, found.getId());
    }

    @Test
    void shouldThrowExceptionWhenSchedulingNotFound() {

        when(schedulingRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                () -> schedulingService.getSchedulingById(1L));
    }

    @Test
    void shouldDeleteScheduling() {

        schedulingService.deleteScheduling(1L);

        verify(schedulingRepository, times(1))
                .deleteById(1L);
    }

    @Test
    void shouldUpdateScheduling() {

        Scheduling existing = new Scheduling();

        SchedulingDTO dto = new SchedulingDTO(
                LocalDate.now(),
                LocalTime.now(),
                SchedulingStatus.DONE,
                1L,
                1L,
                1L
        );

        Client client = new Client();
        Professional professional = new Professional();
        Procedure procedure = new Procedure();

        when(schedulingRepository.findById(1L))
                .thenReturn(Optional.of(existing));

        when(clientRepository.findById(1L))
                .thenReturn(Optional.of(client));

        when(professionalRepository.findById(1L))
                .thenReturn(Optional.of(professional));

        when(procedureRepository.findById(1L))
                .thenReturn(Optional.of(procedure));

        when(schedulingRepository.save(existing))
                .thenReturn(existing);

        Scheduling result =
                schedulingService.updateScheduling(1L, dto);

        assertEquals(SchedulingStatus.DONE,
                result.getStatus());
    }


    @Test
    void shouldThrowExceptionWhenUpdatingSchedulingNotFound() {

        SchedulingDTO dto = new SchedulingDTO(
                LocalDate.now(),
                LocalTime.now(),
                SchedulingStatus.SCHEDULED,
                1L,
                1L,
                1L
        );

        when(schedulingRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,() -> schedulingService.updateScheduling(1L, dto));
    }
}