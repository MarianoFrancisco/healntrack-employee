package com.sa.healntrack.employee_service.department.application.port.in.create_department;

public record CreateDepartmentCommand(
    String name,
    String code,
    String description
) {}
