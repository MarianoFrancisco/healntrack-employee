package com.sa.healntrack.employee_service.employment.infrastructure.adapter.in.rest.mapper;

import com.sa.healntrack.employee_service.department.infrastructure.adapter.in.rest.DepartmentRestMapper;
import com.sa.healntrack.employee_service.employment.domain.Employee;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.in.rest.dto.EmployeeResponseDTO;

public class EmployeeRestMapper {

    public static EmployeeResponseDTO toResponseDTO(Employee employee) {
        return new EmployeeResponseDTO(
                employee.getId().value(),
                employee.getCui().value(),
                employee.getNit().value(),
                employee.getFullname(),
                employee.getEmail().value(),
                employee.getPhoneNumber().value(),
                employee.getBirthDate(),
                DepartmentRestMapper.toResponseDTO(employee.getDepartment()),
                employee.getSalary(),
                employee.getIgssPercent(),
                employee.getIrtraPercent(),
                employee.isActive()
        );
    }
}

