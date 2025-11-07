package com.sa.healntrack.employee_service.department.application.port.out.persistence;

import com.sa.healntrack.employee_service.department.domain.Department;

public interface StoreDepartment {
    Department save(Department department);
}
