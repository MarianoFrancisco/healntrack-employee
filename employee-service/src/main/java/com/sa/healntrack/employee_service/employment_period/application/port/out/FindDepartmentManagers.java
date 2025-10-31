package com.sa.healntrack.employee_service.employment_period.application.port.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.sa.healntrack.employee_service.employment_period.application.port.in.find_department_managers.FindAllDepartmentManagersQuery;
import com.sa.healntrack.employee_service.employment_period.domain.DepartmentManager;
import com.sa.healntrack.employee_service.employment_period.domain.Employee;

public interface FindDepartmentManagers {
    List<DepartmentManager> findAllDepartmentManagers(FindAllDepartmentManagersQuery query);
    Optional<DepartmentManager> findDepartmentManagerById(UUID id);
    Optional<DepartmentManager> findDepartmentManagerByEmployee(Employee employee);
    boolean existByDepartmentCodeAndIsActive(String departmentCode, boolean isActive);
}
