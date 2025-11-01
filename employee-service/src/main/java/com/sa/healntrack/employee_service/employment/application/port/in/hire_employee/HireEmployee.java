package com.sa.healntrack.employee_service.employment.application.port.in.hire_employee;

import com.sa.healntrack.employee_service.employment.domain.Employee;

public interface HireEmployee {
    Employee hireEmployee(HireEmployeeCommand command);
}
