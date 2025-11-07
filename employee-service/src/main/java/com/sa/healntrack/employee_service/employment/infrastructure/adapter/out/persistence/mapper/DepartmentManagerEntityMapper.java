package com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.mapper;

import com.sa.healntrack.employee_service.department.infrastructure.adapter.out.persistence.DepartmentEntity;
import com.sa.healntrack.employee_service.department.infrastructure.adapter.out.persistence.DepartmentEntityMapper;
import com.sa.healntrack.employee_service.employment.domain.*;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.entity.DepartmentManagerEntity;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.entity.EmployeeEntity;

public class DepartmentManagerEntityMapper {

    public static DepartmentManager toDomain(DepartmentManagerEntity entity) {
        if (entity == null) {
            return null;
        }

        Employee employee = EmployeeEntityMapper.toDomain(entity.getEmployee());

        DepartmentManager manager = new DepartmentManager(
                entity.getId(),
                employee,
                DepartmentEntityMapper.toDomain(entity.getDepartment()),
                entity.getStartDate()
        );

        if (entity.getEndDate() != null) {
            manager.endManagement(entity.getEndDate());
        }

        return manager;
    }

    public static DepartmentManagerEntity toEntity(DepartmentManager manager) {
        if (manager == null) {
            return null;
        }

        EmployeeEntity employeeEntity = EmployeeEntityMapper.toEntity(manager.getEmployee());
        DepartmentEntity departmentEntity = DepartmentEntityMapper.toEntity(manager.getDepartment());

        return DepartmentManagerEntity.builder()
                .id(manager.getId().value())
                .employee(employeeEntity)
                .department(departmentEntity)
                .startDate(manager.getStartDate())
                .endDate(manager.getEndDate())
                .isActive(manager.isActive())
                .build();
    }
}

