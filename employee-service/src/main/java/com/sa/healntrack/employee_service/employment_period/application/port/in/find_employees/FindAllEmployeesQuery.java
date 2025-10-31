package com.sa.healntrack.employee_service.employment_period.application.port.in.find_employees;

public record FindAllEmployeesQuery(
    String searchTerm,
    String department,
    Boolean isActive
) {}
