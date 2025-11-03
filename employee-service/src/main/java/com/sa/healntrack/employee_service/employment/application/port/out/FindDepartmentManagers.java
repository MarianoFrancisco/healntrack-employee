package com.sa.healntrack.employee_service.employment.application.port.out;

import java.util.List;
import java.util.Optional;

import com.sa.healntrack.employee_service.department.domain.Department;
import com.sa.healntrack.employee_service.employment.application.port.in.find_department_managers.FindAllDepartmentManagersQuery;
import com.sa.healntrack.employee_service.employment.domain.DepartmentManager;
import com.sa.healntrack.employee_service.employment.domain.Employee;

public interface FindDepartmentManagers {
    List<DepartmentManager> findAllDepartmentManagers(FindAllDepartmentManagersQuery query);
    Optional<DepartmentManager> findDepartmentManagerByEmployeeAndIsActive(Employee employee, boolean isActive);
    Optional<DepartmentManager> findByEmailAndIsActive(String email, boolean isActive);
    boolean existsByDepartmentAndIsActive(Department department, boolean isActive);
}
