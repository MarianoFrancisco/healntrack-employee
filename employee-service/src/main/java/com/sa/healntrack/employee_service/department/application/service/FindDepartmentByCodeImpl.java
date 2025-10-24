package com.sa.healntrack.employee_service.department.application.service;

import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.department.application.exception.DepartmentNotFoundException;
import com.sa.healntrack.employee_service.department.application.port.in.find_department_by_code.FindDepartmentByCode;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.FindDepartments;
import com.sa.healntrack.employee_service.department.domain.Department;

@Transactional(readOnly = true)
public class FindDepartmentByCodeImpl implements FindDepartmentByCode {
    private final FindDepartments findDepartments;
    
    public FindDepartmentByCodeImpl(FindDepartments findDepartments) {
        this.findDepartments = findDepartments;
    }

    @Override
    public Department findByCode(String code) {
        return findDepartments.findDepartmentByCode(code)
            .orElseThrow(() -> new DepartmentNotFoundException(code));
    }

    
}
