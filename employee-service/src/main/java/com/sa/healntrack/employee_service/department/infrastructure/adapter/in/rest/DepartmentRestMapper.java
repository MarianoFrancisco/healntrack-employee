package com.sa.healntrack.employee_service.department.infrastructure.adapter.in.rest;

import com.sa.healntrack.employee_service.department.domain.Department;
import com.sa.healntrack.employee_service.department.infrastructure.adapter.in.rest.dto.DepartmentResponseDTO;

public class DepartmentRestMapper {
    public static DepartmentResponseDTO toResponseDTO(Department department) {
        return new DepartmentResponseDTO(
                department.getCode().value(),
                department.getName(),
                department.getDescription(),
                department.isActive());
    }
}
