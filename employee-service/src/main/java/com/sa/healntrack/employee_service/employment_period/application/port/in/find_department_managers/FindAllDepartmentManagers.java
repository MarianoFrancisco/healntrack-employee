package com.sa.healntrack.employee_service.employment_period.application.port.in.find_department_managers;

import java.util.List;

import com.sa.healntrack.employee_service.employment_period.domain.DepartmentManager;

public interface FindAllDepartmentManagers {
    List<DepartmentManager> findAllDepartmentManagers(FindAllDepartmentManagersQuery query);
}
