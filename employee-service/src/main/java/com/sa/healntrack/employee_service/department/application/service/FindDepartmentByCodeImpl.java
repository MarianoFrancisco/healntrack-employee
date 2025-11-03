package com.sa.healntrack.employee_service.department.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.department.application.exception.DepartmentNotFoundException;
import com.sa.healntrack.employee_service.department.application.port.in.find_department_by_code.FindDepartmentByCode;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.FindDepartments;
import com.sa.healntrack.employee_service.department.domain.Department;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FindDepartmentByCodeImpl implements FindDepartmentByCode {
    private final FindDepartments findDepartments;

    @Override
    public Department findByCode(String code) {
        return findDepartments.findDepartmentByCode(code)
            .orElseThrow(() -> new DepartmentNotFoundException(code));
    }

    
}
