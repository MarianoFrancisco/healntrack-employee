package com.sa.healntrack.employee_service.employment.application.port.in.find_employees;

import java.util.UUID;

import com.sa.healntrack.employee_service.employment.domain.Employee;

public interface FindEmployeeById {
    Employee findById(UUID id);
}
