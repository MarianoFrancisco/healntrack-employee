package com.sa.healntrack.employee_service.department.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.department.application.exception.DepartmentNotFoundException;
import com.sa.healntrack.employee_service.department.application.port.in.deactivate_department.DeactivateDepartment;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.FindDepartments;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.StoreDepartment;
import com.sa.healntrack.employee_service.department.domain.Department;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class DeactivateDepartmentImpl implements DeactivateDepartment {
    private final StoreDepartment storeDepartment;
    private final FindDepartments findDepartments;

    @Override
    public void deactivateDepartment(String code) {
        Department department = findDepartments.findDepartmentByCode(code)
            .orElseThrow(() -> new DepartmentNotFoundException(code));

        // TODO: Check for related active employees before deactivation
        department.deactivate();

        storeDepartment.save(department);
    }
    
}
