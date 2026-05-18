package com.AuraSkin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.AuraSkin.dto.ProfessionalDTO;
import com.AuraSkin.entity.Professional;
import com.AuraSkin.service.ProfessionalService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/professionals")
public class ProfessionalController {

    private final ProfessionalService service;

    public ProfessionalController(ProfessionalService service) {
        this.service = service;
    }

    @GetMapping
    public List<Professional> getAllProfessionals() {
        return service.getAllProfessionals();
    }

    @PostMapping
    public Professional createProfessional(@RequestBody @Valid ProfessionalDTO dto) {
        
        Professional professional = new Professional();
        professional.setName(dto.getName());
        professional.setSpecialty(dto.getSpecialty());

        return service.saveProfessional(professional);
    }

    @GetMapping("/{id}")
    public Professional getProfessionalById(@PathVariable Long id) {

        return service.getProfessionalById(id);
    }

    @PutMapping("/{id}")
    public Professional updateProfessional(@PathVariable Long id,
        @RequestBody Professional professional) {

        return service.updateProfessional(id, professional);
    }

    @DeleteMapping("/{id}")
    public void deleteProfessional(@PathVariable Long id) {
        service.deleteProfessional(id);
    }
}
