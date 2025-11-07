package com.sa.healntrack.employee_service.department.application.port.in.create_department;

import com.sa.healntrack.employee_service.department.domain.Department;

public interface CreateDepartment {
    Department createDepartment(CreateDepartmentCommand command);
}
