package com.AuraSkin.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.AuraSkin.entity.Professional;
import com.AuraSkin.repository.ProfessionalRepository;

@Service
public class ProfessionalService {
    
    private final ProfessionalRepository repository;

    public ProfessionalService(ProfessionalRepository repository) {
        this.repository = repository;
    }
    
    public Professional saveProfessional(Professional professional) {
        return repository.save(professional);
    }

    public List<Professional> getAllProfessionals() { 
        return repository.findAll();
    }

    public Professional getProfessionalById(Long id) {

        return repository.findById(id).orElseThrow(() -> new RuntimeException("Professional not found"));
    }

    public Professional updateProfessional(Long id, Professional professional) {
        Professional existingProfessional = repository.findById(id).orElseThrow(() -> new RuntimeException("Professional not found"));

        if (existingProfessional != null) {

            if(professional.getName() != null){
                existingProfessional.setName(professional.getName());
            }
            if(professional.getSpecialty() != null){
                existingProfessional.setSpecialty(professional.getSpecialty());
            }

            return repository.save(existingProfessional);
        }

        throw new RuntimeException("Professional not found");
    }

    public void deleteProfessional(Long id) {
        repository.deleteById(id);
    }

}
