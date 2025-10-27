package com.sa.healntrack.employee_service.employment_period.application.port.in.hire_employee;

import java.math.BigDecimal;
import java.time.LocalDate;

public record HireEmployeeCommand(
    String cui,
    String nit,
    String fullname,
    String email,
    String phoneNumber,
    LocalDate birthDate,
    String departmentCode,
    BigDecimal salary,
    BigDecimal igssPercent,
    BigDecimal irtraPercent
) {}
