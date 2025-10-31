package com.sa.healntrack.employee_service.employment_period.infrastructure.adapter.out.persistence.mapper;

import com.sa.healntrack.employee_service.employment_period.domain.*;
import com.sa.healntrack.employee_service.employment_period.infrastructure.adapter.out.persistence.entity.EmployeeEntity;
import com.sa.healntrack.employee_service.employment_period.infrastructure.adapter.out.persistence.entity.EmploymentPeriodEntity;

public class EmploymentPeriodEntityMapper {

    public static EmploymentPeriod toDomain(EmploymentPeriodEntity entity) {
        if (entity == null) {
            return null;
        }

        Employee employee = EmployeeEntityMapper.toDomain(entity.getEmployee());

        EmploymentPeriod period = new EmploymentPeriod(
                entity.getId(),
                employee,
                entity.getType(),
                entity.getStartDate(),
                entity.getSalary(),
                entity.getNotes()
        );

        if (entity.getEndDate() != null) {
            period.endPeriod(entity.getEndDate());
        }

        return period;
    }

    public static EmploymentPeriodEntity toEntity(EmploymentPeriod period) {
        if (period == null) {
            return null;
        }

        EmployeeEntity employeeEntity = EmployeeEntityMapper.toEntity(period.getEmployee());

        return EmploymentPeriodEntity.builder()
                .id(period.getId().value())
                .employee(employeeEntity)
                .type(period.getType())
                .startDate(period.getStartDate())
                .endDate(period.getEndDate())
                .salary(period.getSalary())
                .notes(period.getNotes())
                .build();
    }
}

