package com.sa.healntrack.employee_service.employment_period.application.port.in.find_employees;

import java.util.List;

import com.sa.healntrack.employee_service.employment_period.domain.Employee;

public interface FindAllEmployees {
    List<Employee> findAllEmployees(FindAllEmployeesQuery query);
}
