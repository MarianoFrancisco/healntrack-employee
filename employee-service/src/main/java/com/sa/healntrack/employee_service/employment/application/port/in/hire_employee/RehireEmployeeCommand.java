package com.sa.healntrack.employee_service.employment.application.port.in.hire_employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RehireEmployeeCommand(
    String phoneNumber,
    String departmentCode,
    BigDecimal newSalary,
    BigDecimal igssPercent,
    BigDecimal irtraPercent,
    LocalDate startDate,
    String notes
) {}
