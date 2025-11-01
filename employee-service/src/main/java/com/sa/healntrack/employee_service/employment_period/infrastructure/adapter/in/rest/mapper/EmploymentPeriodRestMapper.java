package com.sa.healntrack.employee_service.employment_period.infrastructure.adapter.in.rest.mapper;

import com.sa.healntrack.employee_service.employment_period.domain.EmploymentPeriod;
import com.sa.healntrack.employee_service.employment_period.infrastructure.adapter.in.rest.dto.EmploymentPeriodResponseDTO;

public class EmploymentPeriodRestMapper {
    public static EmploymentPeriodResponseDTO toResponseDTO(EmploymentPeriod employmentPeriod) {
        return new EmploymentPeriodResponseDTO(
                employmentPeriod.getId().value(),
                employmentPeriod.getEmployee().getCui().value(),
                employmentPeriod.getEmployee().getFullname(),
                employmentPeriod.getDepartment().getCode().value(),
                employmentPeriod.getDepartment().getName(),
                employmentPeriod.getType(),
                employmentPeriod.getStartDate(),
                employmentPeriod.getEndDate(),
                employmentPeriod.getSalary(),
                employmentPeriod.getNotes()
        );
    }
}
