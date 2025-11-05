package com.sa.healntrack.employee_service.employment.infrastructure.adapter.in.rest.dto;

import java.time.LocalDate;

import com.sa.healntrack.employee_service.employment.domain.PeriodType;

import jakarta.validation.constraints.NotNull;

public record TerminateEmploymentRequestDTO(
    @NotNull(message = "La fecha de terminación es obligatoria")
    LocalDate date,
    @NotNull(message = "El tipo de terminación es obligatorio")
    PeriodType terminationType,
    String reason
) {
}
