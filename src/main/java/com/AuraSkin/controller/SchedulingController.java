package com.AuraSkin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.AuraSkin.dto.SchedulingDTO;
import com.AuraSkin.entity.Scheduling;
import com.AuraSkin.service.SchedulingService;

@RestController
@RequestMapping("/schedulings")
public class SchedulingController {

    private final SchedulingService schedulingService;

    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @GetMapping
    public List<Scheduling> getAllSchedulings() {
        return schedulingService.getAllSchedulings();
    }

    @PostMapping
    public Scheduling createScheduling(
            @RequestBody SchedulingDTO dto) {

        return schedulingService.saveScheduling(dto);
    }

    @GetMapping("/{id}")
    public Scheduling getSchedulingById(
            @PathVariable Long id) {

        return schedulingService.getSchedulingById(id);
    }

    @PutMapping("/{id}")
    public Scheduling updateScheduling(
            @PathVariable Long id,
            @RequestBody SchedulingDTO dto) {

        return schedulingService.updateScheduling(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteScheduling(
            @PathVariable Long id) {

        schedulingService.deleteScheduling(id);

        return "Scheduling with ID " + id + " has been deleted.";
    }
}
