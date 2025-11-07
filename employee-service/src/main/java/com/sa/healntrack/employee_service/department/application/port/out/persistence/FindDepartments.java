package com.sa.healntrack.employee_service.department.application.port.out.persistence;

import java.util.List;
import java.util.Optional;

import com.sa.healntrack.employee_service.department.application.port.in.find_all_departments.FindAllDepartmentsQuery;
import com.sa.healntrack.employee_service.department.domain.Department;


public interface FindDepartments {
    List<Department> findAllDepartments(FindAllDepartmentsQuery query);
    Optional<Department> findDepartmentByCode(String code);
    boolean existsByCodeAndIsActive(String code, boolean isActive);
}
