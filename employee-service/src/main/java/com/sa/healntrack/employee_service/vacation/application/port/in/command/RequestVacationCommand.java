package com.sa.healntrack.employee_service.vacation.application.port.in.command;

import java.time.LocalDate;

public record RequestVacationCommand(
    String employeeCui,
    LocalDate requestedAt,
    LocalDate startDate,
    LocalDate endDate
) {}
