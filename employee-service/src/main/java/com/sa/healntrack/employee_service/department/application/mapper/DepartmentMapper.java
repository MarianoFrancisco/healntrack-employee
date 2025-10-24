package com.sa.healntrack.employee_service.department.application.mapper;

import com.sa.healntrack.employee_service.department.application.port.in.create_department.CreateDepartmentCommand;
import com.sa.healntrack.employee_service.department.application.port.in.update_department.UpdateDepartmentCommand;
import com.sa.healntrack.employee_service.department.domain.Department;
import com.sa.healntrack.employee_service.department.domain.DepartmentCode;

public class DepartmentMapper {
    private DepartmentMapper() {
        // Private constructor to prevent instantiation
    }

    public static Department toDomain(CreateDepartmentCommand command) {
        return new Department(
            new DepartmentCode(command.code()),
            command.name(),
            command.description()
        );
    }

    public static Department updateDepartment(Department existing, UpdateDepartmentCommand command) {
        String updatedName = command.name() != null ? command.name() : existing.getName();
        String updatedDescription = command.description() != null ? command.description() : existing.getDescription();

        Department updated = new Department(
            existing.getCode(),
            updatedName,
            updatedDescription
        );

        if (!existing.isActive()) {
            updated.deactivate();
        }
        return updated;
    }
}
