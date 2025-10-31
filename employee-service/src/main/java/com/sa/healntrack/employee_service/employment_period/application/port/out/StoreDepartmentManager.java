package com.sa.healntrack.employee_service.employment_period.application.port.out;

import com.sa.healntrack.employee_service.employment_period.domain.DepartmentManager;

public interface StoreDepartmentManager {
    DepartmentManager save(DepartmentManager departmentManager);
}
