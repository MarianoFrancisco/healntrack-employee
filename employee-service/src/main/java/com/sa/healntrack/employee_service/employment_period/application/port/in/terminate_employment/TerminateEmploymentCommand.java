package com.sa.healntrack.employee_service.employment_period.application.port.in.terminate_employment;

import java.time.LocalDate;
import java.util.UUID;

import com.sa.healntrack.employee_service.employment_period.domain.PeriodType;

public record TerminateEmploymentCommand(
    UUID employeeId,
    LocalDate date,
    PeriodType terminationType,
    String reason
) {}
