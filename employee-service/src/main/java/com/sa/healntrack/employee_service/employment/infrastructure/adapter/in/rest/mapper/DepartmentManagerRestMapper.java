package com.sa.healntrack.employee_service.employment.infrastructure.adapter.in.rest.mapper;

import com.sa.healntrack.employee_service.employment.domain.DepartmentManager;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.in.rest.dto.DepartmentManagerResponseDTO;

public class DepartmentManagerRestMapper {
    public static DepartmentManagerResponseDTO toResponseDTO(DepartmentManager departmentManager) {
        return new DepartmentManagerResponseDTO(
            departmentManager.getId().value(),
            departmentManager.getDepartment().getCode().value(),
            departmentManager.getDepartment().getName(),
            departmentManager.getEmployee().getCui().value(),
            departmentManager.getEmployee().getFullname(),
            departmentManager.getStartDate(),
            departmentManager.getEndDate(),
            departmentManager.isActive()
        );
    }
}
