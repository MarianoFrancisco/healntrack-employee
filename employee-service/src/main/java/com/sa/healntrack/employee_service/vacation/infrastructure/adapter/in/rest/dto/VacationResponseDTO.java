package com.sa.healntrack.employee_service.vacation.infrastructure.adapter.in.rest.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.sa.healntrack.employee_service.vacation.domain.VacationStatus;

public record VacationResponseDTO(
    UUID id,
    String employeeCui,
    String employeeName,
    String departmentCode,
    String departmentName,

    LocalDate startDate,
    LocalDate endDate,
    LocalDate requestedAt,
    String reviewedBy,
    LocalDate reviewedAt,
    VacationStatus status
) {}
