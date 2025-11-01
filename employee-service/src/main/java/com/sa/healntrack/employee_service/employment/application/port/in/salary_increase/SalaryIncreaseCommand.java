package com.sa.healntrack.employee_service.employment.application.port.in.salary_increase;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SalaryIncreaseCommand(
    BigDecimal salaryIncrease,
    LocalDate date,
    String notes
) {}
