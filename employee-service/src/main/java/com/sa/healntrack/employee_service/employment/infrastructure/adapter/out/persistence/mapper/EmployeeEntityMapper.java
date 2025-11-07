package com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.mapper;

import com.sa.healntrack.employee_service.department.domain.Department;
import com.sa.healntrack.employee_service.department.infrastructure.adapter.out.persistence.DepartmentEntity;
import com.sa.healntrack.employee_service.department.infrastructure.adapter.out.persistence.DepartmentEntityMapper;
import com.sa.healntrack.employee_service.employment.domain.*;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.entity.EmployeeEntity;

public class EmployeeEntityMapper {

    public static Employee toDomain(EmployeeEntity entity) {
        if (entity == null) {
            return null;
        }

        Department department = DepartmentEntityMapper.toDomain(entity.getDepartment());

        Employee employee = new Employee(
                entity.getId(),
                entity.getCui(),
                entity.getNit(),
                entity.getFullname(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getBirthDate(),
                department,
                entity.getSalary(),
                entity.getIgssPercent(),
                entity.getIrtraPercent()
        );

        if (!entity.isActive()) {
            employee.deactivate();
        }

        return employee;
    }

    public static EmployeeEntity toEntity(Employee employee) {
        if (employee == null) {
            return null;
        }

        DepartmentEntity departmentEntity = DepartmentEntityMapper.toEntity(employee.getDepartment());

        return EmployeeEntity.builder()
                .id(employee.getId().value())
                .cui(employee.getCui().value())
                .nit(employee.getNit().value())
                .fullname(employee.getFullname())
                .email(employee.getEmail().value())
                .phoneNumber(employee.getPhoneNumber().value())
                .birthDate(employee.getBirthDate())
                .department(departmentEntity)
                .salary(employee.getSalary())
                .igssPercent(employee.getIgssPercent())
                .irtraPercent(employee.getIrtraPercent())
                .isActive(employee.isActive())
                .build();
    }
}

