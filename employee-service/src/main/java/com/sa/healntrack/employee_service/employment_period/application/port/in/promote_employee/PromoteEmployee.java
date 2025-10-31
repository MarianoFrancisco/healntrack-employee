package com.sa.healntrack.employee_service.employment_period.application.port.in.promote_employee;

import com.sa.healntrack.employee_service.employment_period.domain.Employee;

public interface PromoteEmployee {
    Employee promoteEmployee(String cui, PromoteEmployeeCommand command);
}
