package com.sa.healntrack.employee_service.vacation.application.port.in.command;

import java.time.LocalDate;

import com.sa.healntrack.employee_service.vacation.domain.VacationStatus;

public record FindAllVacationsQuery(
    String employee,
    String department,
    LocalDate startDate,
    LocalDate endDate,
    LocalDate requestedAtFrom,
    LocalDate requestedAtTo,
    VacationStatus status
) {}
