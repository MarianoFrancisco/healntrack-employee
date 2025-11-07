package com.sa.healntrack.employee_service.department.infrastructure.adapter.out.persistence;

import com.sa.healntrack.employee_service.department.domain.Department;

public class DepartmentEntityMapper {

    public static Department toDomain(DepartmentEntity entity) {
        Department department = new Department(
                entity.getCode(),
                entity.getName(),
                entity.getDescription()
        );

        if (!entity.isActive()) {
            department.deactivate();
        }

        return department;
    }

    public static DepartmentEntity toEntity(Department department) {
        return DepartmentEntity.builder()
                .code(department.getCode().value())
                .name(department.getName())
                .description(department.getDescription())
                .isActive(department.isActive())
                .build();
    }
}
