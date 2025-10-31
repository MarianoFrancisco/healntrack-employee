package com.sa.healntrack.employee_service.employment_period.application.port.in.promote_employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public record PromoteEmployeeCommand(
    BigDecimal salaryIncrease,
    String departmentCode,
    LocalDate date,
    String notes
) {}
