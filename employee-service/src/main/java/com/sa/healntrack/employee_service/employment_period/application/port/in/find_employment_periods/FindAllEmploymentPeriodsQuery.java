package com.sa.healntrack.employee_service.employment_period.application.port.in.find_employment_periods;

import java.time.LocalDate;

import com.sa.healntrack.employee_service.employment_period.domain.PeriodType;

public record FindAllEmploymentPeriodsQuery(
    String employee,
    PeriodType type,
    LocalDate startDateFrom,
    LocalDate startDateTo,
    LocalDate endDateFrom,
    LocalDate endDateTo
    
) {}
