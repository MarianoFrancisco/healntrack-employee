package com.sa.healntrack.employee_service.employment.application.port.in.update_employee;

import com.sa.healntrack.employee_service.employment.domain.Employee;

public interface UpdateEmployee {
    Employee updateEmployee(String cui, UpdateEmployeeCommand command);
}
