package com.sa.healntrack.employee_service.vacation.application.port.in.command;

import java.time.LocalDate;

public record ReviewVacationCommand(
    String reviewer,
    LocalDate reviewedAt
) {}
