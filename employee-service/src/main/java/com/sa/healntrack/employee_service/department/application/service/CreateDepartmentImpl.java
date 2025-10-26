package com.sa.healntrack.employee_service.department.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sa.healntrack.employee_service.department.application.exception.DuplicateDepartmentException;
import com.sa.healntrack.employee_service.department.application.mapper.DepartmentMapper;
import com.sa.healntrack.employee_service.department.application.port.in.create_department.CreateDepartment;
import com.sa.healntrack.employee_service.department.application.port.in.create_department.CreateDepartmentCommand;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.FindDepartments;
import com.sa.healntrack.employee_service.department.application.port.out.persistence.StoreDepartment;
import com.sa.healntrack.employee_service.department.domain.Department;

@Service
@Transactional(rollbackFor = Exception.class)
public class CreateDepartmentImpl implements CreateDepartment {
    private final StoreDepartment storeDepartment;
    private final FindDepartments findDepartments;

    public CreateDepartmentImpl(StoreDepartment storeDepartment, FindDepartments findDepartments) {
        this.storeDepartment = storeDepartment;
        this.findDepartments = findDepartments;
    }

    @Override
    public Department createDepartment(CreateDepartmentCommand command) {
        if (findDepartments.existsByCodeAndIsActive(command.code(), true)) {
            throw new DuplicateDepartmentException(command.code());
        }
        
        Department department = DepartmentMapper.toDomain(command);
        storeDepartment.save(department);
        return department;
    }
}
