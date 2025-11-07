package com.sa.healntrack.employee_service.employment.application.port.in.salary_increase;

import com.sa.healntrack.employee_service.employment.domain.Employee;

public interface SalaryIncrease {
    Employee applySalaryIncrease(String cui, SalaryIncreaseCommand command);
}
