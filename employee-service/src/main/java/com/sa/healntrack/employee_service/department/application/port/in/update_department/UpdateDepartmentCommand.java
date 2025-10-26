package com.sa.healntrack.employee_service.department.application.port.in.update_department;

public record UpdateDepartmentCommand(
    String name,
    String description
) {}
