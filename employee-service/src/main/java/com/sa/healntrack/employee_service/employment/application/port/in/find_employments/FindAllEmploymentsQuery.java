package com.sa.healntrack.employee_service.employment.application.port.in.find_employments;

import java.time.LocalDate;

import com.sa.healntrack.employee_service.employment.domain.PeriodType;

public record FindAllEmploymentsQuery(
    String employee,
    String department,
    PeriodType type,
    LocalDate startDateFrom,
    LocalDate startDateTo,
    LocalDate endDateFrom,
    LocalDate endDateTo
    
) {}
