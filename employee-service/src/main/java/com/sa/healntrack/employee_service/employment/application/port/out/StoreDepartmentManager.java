package com.sa.healntrack.employee_service.employment.application.port.out;

import com.sa.healntrack.employee_service.employment.domain.DepartmentManager;

public interface StoreDepartmentManager {
    DepartmentManager save(DepartmentManager departmentManager);
}
