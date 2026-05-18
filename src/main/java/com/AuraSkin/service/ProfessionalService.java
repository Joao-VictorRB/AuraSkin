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
        return repository.findById(id).orElse(null);
    }

    public Professional updateProfessional(Long id, Professional professional) {
        Professional existingProfessional = repository.findById(id).orElse(null);

        if (existingProfessional != null) {

            if(professional.getName() != null){
                existingProfessional.setName(professional.getName());
            }
            if(professional.getSpecialty() != null){
                existingProfessional.setSpecialty(professional.getSpecialty());
            }

            return repository.save(existingProfessional);
        }

        return null;
    }

    public void deleteProfessional(Long id) {
        repository.deleteById(id);
    }

}
