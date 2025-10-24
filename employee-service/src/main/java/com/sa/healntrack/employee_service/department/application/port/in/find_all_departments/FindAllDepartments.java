package com.sa.healntrack.employee_service.department.application.port.in.find_all_departments;

import java.util.List;

import com.sa.healntrack.employee_service.department.domain.Department;

public interface FindAllDepartments {
    List<Department> findAllDepartments(FindAllDepartmentsCommand command);
}
