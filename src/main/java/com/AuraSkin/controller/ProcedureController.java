package com.AuraSkin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.AuraSkin.dto.ProcedureDTO;
import com.AuraSkin.entity.Procedure;
import com.AuraSkin.service.ProcedureService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/procedures")
public class ProcedureController {

    private final ProcedureService procedureService;

    public ProcedureController(ProcedureService procedure) {
        this.procedureService = procedure;
    }

    @GetMapping
    public List<Procedure> getAllProcedures() {
        return procedureService.getAllProcedures();
    }

    @PostMapping
    public Procedure createProcedure(@RequestBody @Valid ProcedureDTO dto) {
        Procedure procedure = new Procedure();
        procedure.setName(dto.getName());
        procedure.setDescription(dto.getDescription());
        procedure.setPrice(dto.getPrice());
        procedure.setDurationMin(dto.getDurationMin());
        return procedureService.saveProcedure(procedure);
    }

    @GetMapping("/{id}")
    public Procedure getProcedureById(@PathVariable Long id) {
        return procedureService.getProcedureById(id);
    }

    @PutMapping("/{id}")
    public Procedure updateProcedure(@PathVariable Long id,
        @RequestBody Procedure procedure) {
        return procedureService.updateProcedure(id, procedure);
    }

    @DeleteMapping("/{id}")
    public String deleteProcedure(@PathVariable Long id) {
        procedureService.deleteProcedure(id);
        return "Procedure with ID " + id + " has been deleted.";
    }
    
}
