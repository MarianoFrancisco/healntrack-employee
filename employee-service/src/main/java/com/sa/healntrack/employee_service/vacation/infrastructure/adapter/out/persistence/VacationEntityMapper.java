package com.sa.healntrack.employee_service.vacation.infrastructure.adapter.out.persistence;

import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.mapper.DepartmentManagerEntityMapper;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.out.persistence.mapper.EmployeeEntityMapper;
import com.sa.healntrack.employee_service.vacation.domain.Vacation;

public class VacationEntityMapper {

    private VacationEntityMapper() {}

    public static Vacation toDomain(VacationEntity entity) {
        if (entity == null) {
            return null;
        }

        return new Vacation(
            entity.getId(),
            EmployeeEntityMapper.toDomain(entity.getEmployee()),
            entity.getRequestedAt().toLocalDate(),
            entity.getStartDate(),
            entity.getEndDate(),
            entity.getStatus(),
            DepartmentManagerEntityMapper.toDomain(entity.getApprovedBy()),
            entity.getApprovedAt(),
            entity.getSignedAt() != null ? entity.getSignedAt().toLocalDate() : null
        );
    }

    public static VacationEntity toEntity(Vacation vacation) {
        if (vacation == null) {
            return null;
        }

        return VacationEntity.builder()
            .id(vacation.getId().value())
            .employee(EmployeeEntityMapper.toEntity(vacation.getEmployee()))
            .requestedAt(vacation.getRequestedAt().atStartOfDay())
            .startDate(vacation.getPeriod().startDate())
            .endDate(vacation.getPeriod().endDate())
            .status(vacation.getStatus())
            .approvedBy(DepartmentManagerEntityMapper.toEntity(vacation.getApprovedBy()))
            .approvedAt(vacation.getApprovedAt())
            .signedAt(vacation.getSignedAt() != null ? vacation.getSignedAt().atStartOfDay() : null)
            .build();
    }
}
