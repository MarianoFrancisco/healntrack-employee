package com.sa.healntrack.employee_service.payroll.application.port.in.command;

import java.time.LocalDate;

import com.sa.healntrack.employee_service.payroll.domain.PayrollType;

public record FindAllPayrollsQuery(
    String employee,
    String department,
    LocalDate paydayFrom,
    LocalDate paydayTo,
    LocalDate startDate,
    LocalDate endDate,
    PayrollType type
) {}
