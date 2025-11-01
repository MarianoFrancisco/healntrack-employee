package com.sa.healntrack.employee_service.employment.infrastructure.adapter.in.rest.dto;

import java.time.LocalDate;

import com.sa.healntrack.employee_service.employment.domain.PeriodType;

public record TerminateEmploymentRequestDTO(
    LocalDate date,
    PeriodType terminationType,
    String reason
) {
}
