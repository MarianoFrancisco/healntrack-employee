package com.sa.healntrack.employee_service.payroll.infrastructure.adapter.in.rest.dto;

import java.time.LocalDate;

import com.sa.healntrack.employee_service.payroll.domain.PayrollType;

public record FindAllPayrollsRequestDTO(
    String employee,
    String department,
    LocalDate paydayFrom,
    LocalDate paydayTo,
    LocalDate startDate,
    LocalDate endDate,
    PayrollType type
) {}
