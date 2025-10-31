package com.sa.healntrack.employee_service.employment_period.application.port.in.salary_increase;

import com.sa.healntrack.employee_service.employment_period.domain.Employee;

public interface SalaryIncrease {
    Employee applySalaryIncrease(String cui, SalaryIncreaseCommand command);
}
