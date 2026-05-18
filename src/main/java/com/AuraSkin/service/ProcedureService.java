package com.AuraSkin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.AuraSkin.entity.Procedure;
import com.AuraSkin.repository.ClientRepository;
import com.AuraSkin.repository.ProcedureRepository;

@Service
public class ProcedureService {

    private final ProcedureRepository repository;

    public ProcedureService(ProcedureRepository repository) {
        this.repository = repository;
    }

    public Procedure saveProcedure(Procedure procedure){
        return repository.save(procedure);
    }

    public List<Procedure> getAllProcedures() { 
        return repository.findAll();
    }

    public Procedure getProcedureById(Long id) {

        return repository.findById(id).orElse(null);
    }

    public Procedure updateProcedure(Long id, Procedure procedure) {

        Procedure existingProcedure = repository.findById(id).orElse(null);

        if (existingProcedure != null) {

            if(procedure.getName() != null){
                existingProcedure.setName(procedure.getName());
            }
            if(procedure.getDescription() != null){
                existingProcedure.setDescription(procedure.getDescription());
            }
            if(procedure.getPrice() != null){
                existingProcedure.setPrice(procedure.getPrice());
            }
            if(procedure.getDurationMin() != null){
                existingProcedure.setDurationMin(procedure.getDurationMin());
            }

            return repository.save(existingProcedure);
        }

        return null;
    }

    public void deleteProcedure(Long id) {

        repository.deleteById(id);
    }
    
}
