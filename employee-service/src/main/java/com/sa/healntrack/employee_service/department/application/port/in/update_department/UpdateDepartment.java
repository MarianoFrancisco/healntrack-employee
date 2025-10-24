package com.sa.healntrack.employee_service.department.application.port.in.update_department;

import com.sa.healntrack.employee_service.department.domain.Department;

public interface UpdateDepartment {
    Department updateDepartment(UpdateDepartmentCommand command);
}
