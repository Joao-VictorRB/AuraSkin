package com.AuraSkin.dto;

import com.AuraSkin.enums.SchedulingStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public record SchedulingDTO(

    LocalDate date,
    LocalTime time,
    SchedulingStatus status,
    Long clientId,
    Long professionalId,
    Long procedureId

) {
}