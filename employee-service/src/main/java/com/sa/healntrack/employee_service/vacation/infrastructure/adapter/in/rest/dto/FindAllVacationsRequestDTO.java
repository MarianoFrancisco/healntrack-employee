package com.sa.healntrack.employee_service.vacation.infrastructure.adapter.in.rest.dto;

import java.time.LocalDate;

import com.sa.healntrack.employee_service.vacation.domain.VacationStatus;

public record FindAllVacationsRequestDTO(
    String employee,
    String department,
    LocalDate startDate,
    LocalDate endDate,
    LocalDate requestedAtFrom,
    LocalDate requestedAtTo,
    VacationStatus status
) {}
