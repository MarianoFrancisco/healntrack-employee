package com.sa.healntrack.employee_service.department.application.port.in.find_all_departments;

public record FindAllDepartmentsQuery(
    String code,
    String name,
    Boolean isActive
) {}
