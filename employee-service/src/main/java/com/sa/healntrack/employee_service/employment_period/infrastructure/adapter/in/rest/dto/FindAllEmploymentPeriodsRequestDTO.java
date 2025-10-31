package com.sa.healntrack.employee_service.employment_period.infrastructure.adapter.in.rest.dto;

import java.time.LocalDate;

import com.sa.healntrack.employee_service.employment_period.domain.PeriodType;

public record FindAllEmploymentPeriodsRequestDTO(
    String employee,
    String department,
    PeriodType type,
    LocalDate startDateFrom,
    LocalDate startDateTo,
    LocalDate endDateFrom,
    LocalDate endDateTo
) {}
