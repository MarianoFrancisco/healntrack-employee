package com.sa.healntrack.employee_service.employment.infrastructure.adapter.in.rest.dto;

import java.time.LocalDate;

import com.sa.healntrack.employee_service.employment.domain.PeriodType;

public record FindAllEmploymentsRequestDTO(
    String employee,
    String department,
    PeriodType type,
    LocalDate startDateFrom,
    LocalDate startDateTo,
    LocalDate endDateFrom,
    LocalDate endDateTo
) {}
