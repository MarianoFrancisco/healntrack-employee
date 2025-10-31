package com.sa.healntrack.employee_service.employment_period.application.port.in.hire_employee;

import com.sa.healntrack.employee_service.employment_period.domain.Employee;

public interface RehireEmployee {
    Employee rehireEmployee(String cui, RehireEmployeeCommand command);
}
