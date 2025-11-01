package com.sa.healntrack.employee_service.employment.application.port.in.terminate_employment;

import java.time.LocalDate;

import com.sa.healntrack.employee_service.employment.domain.PeriodType;

public record TerminateEmploymentCommand(
    LocalDate date,
    PeriodType terminationType,
    String reason
) {}
