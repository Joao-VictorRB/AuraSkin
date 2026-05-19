package com.AuraSkin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.AuraSkin.dto.SchedulingDTO;
import com.AuraSkin.entity.Client;
import com.AuraSkin.entity.Procedure;
import com.AuraSkin.entity.Professional;
import com.AuraSkin.entity.Scheduling;
import com.AuraSkin.repository.ClientRepository;
import com.AuraSkin.repository.ProcedureRepository;
import com.AuraSkin.repository.ProfessionalRepository;
import com.AuraSkin.repository.SchedulingRepository;

@Service
public class SchedulingService {

    private final SchedulingRepository schedulingRepository;
    private final ClientRepository clientRepository;
    private final ProfessionalRepository professionalRepository;
    private final ProcedureRepository procedureRepository;

    public SchedulingService(
            SchedulingRepository schedulingRepository,
            ClientRepository clientRepository,
            ProfessionalRepository professionalRepository,
            ProcedureRepository procedureRepository) {

        this.schedulingRepository = schedulingRepository;
        this.clientRepository = clientRepository;
        this.professionalRepository = professionalRepository;
        this.procedureRepository = procedureRepository;
    }

    public Scheduling saveScheduling(SchedulingDTO dto) {

        Client client = clientRepository.findById(dto.clientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Professional professional = professionalRepository.findById(dto.professionalId())
                .orElseThrow(() -> new RuntimeException("Professional not found"));

        Procedure procedure = procedureRepository.findById(dto.procedureId())
                .orElseThrow(() -> new RuntimeException("Procedure not found"));

        Scheduling scheduling = new Scheduling();

        scheduling.setDate(dto.date());
        scheduling.setTime(dto.time());
        scheduling.setStatus(dto.status());

        scheduling.setClient(client);
        scheduling.setProfessional(professional);
        scheduling.setProcedure(procedure);

        return schedulingRepository.save(scheduling);
    }

    public List<Scheduling> getAllSchedulings() {
        return schedulingRepository.findAll();
    }

    public Scheduling getSchedulingById(Long id) {
        return schedulingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Scheduling not found"));
    }

    public Scheduling updateScheduling(Long id, SchedulingDTO dto) {

        Scheduling scheduling = getSchedulingById(id);

        Client client = clientRepository.findById(dto.clientId())
                .orElseThrow(() -> new RuntimeException("Client not found"));

        Professional professional = professionalRepository.findById(dto.professionalId())
                .orElseThrow(() -> new RuntimeException("Professional not found"));

        Procedure procedure = procedureRepository.findById(dto.procedureId())
                .orElseThrow(() -> new RuntimeException("Procedure not found"));

        scheduling.setDate(dto.date());
        scheduling.setTime(dto.time());
        scheduling.setStatus(dto.status());

        scheduling.setClient(client);
        scheduling.setProfessional(professional);
        scheduling.setProcedure(procedure);

        return schedulingRepository.save(scheduling);
    }

    public void deleteScheduling(Long id) {
        schedulingRepository.deleteById(id);
    }
}