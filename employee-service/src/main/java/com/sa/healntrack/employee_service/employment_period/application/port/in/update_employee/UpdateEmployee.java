package com.sa.healntrack.employee_service.employment_period.application.port.in.update_employee;

import com.sa.healntrack.employee_service.employment_period.domain.Employee;

public interface UpdateEmployee {
    Employee updateEmployee(String cui, UpdateEmployeeCommand command);
}
