package com.sa.healntrack.employee_service.department.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.department.application.exception.DepartmentNotFoundException;
import com.sa.healntrack.employee_service.department.application.mapper.DepartmentMapper;
import com.sa.healntrack.employee_service.department.application.port.in.update_department.UpdateDepartment;
import com.sa.healntrack.employee_service.department.application.port.in.update_department.UpdateDepartmentCommand;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.FindDepartments;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.StoreDepartment;
import com.sa.healntrack.employee_service.department.domain.Department;

@Service
@Transactional(rollbackFor = Exception.class)
public class UpdateDepartmentImpl implements UpdateDepartment {
    private final StoreDepartment storeDepartment;
    private final FindDepartments findDepartments;

    public UpdateDepartmentImpl(StoreDepartment storeDepartment, FindDepartments findDepartments) {
        this.storeDepartment = storeDepartment;
        this.findDepartments = findDepartments;
    }

    @Override
    public Department updateDepartment(String code, UpdateDepartmentCommand command) {
        Department existing = findDepartments.findDepartmentByCode(code)
            .orElseThrow(() -> new DepartmentNotFoundException(code));

        Department updated = DepartmentMapper.updateDepartment(existing, command);
        return storeDepartment.save(updated);
    }
        
}
