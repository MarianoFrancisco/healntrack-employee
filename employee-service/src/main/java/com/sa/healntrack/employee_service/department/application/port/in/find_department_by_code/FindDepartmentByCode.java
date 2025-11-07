package com.sa.healntrack.employee_service.department.application.port.in.find_department_by_code;

import com.sa.healntrack.employee_service.department.domain.Department;

public interface FindDepartmentByCode {
    Department findByCode(String code);
}
