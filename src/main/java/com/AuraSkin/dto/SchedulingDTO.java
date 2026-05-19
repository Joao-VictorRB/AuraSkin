package com.AuraSkin.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record SchedulingDTO(

    LocalDate date,
    LocalTime time,
    String status,
    Long clientId,
    Long professionalId,
    Long procedureId

) {
}