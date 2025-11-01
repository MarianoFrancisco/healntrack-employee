package com.sa.healntrack.employee_service.employment.infrastructure.adapter.in.rest.mapper;

import com.sa.healntrack.employee_service.employment.domain.Employment;
import com.sa.healntrack.employee_service.employment.infrastructure.adapter.in.rest.dto.EmploymentResponseDTO;

public class EmploymentRestMapper {
    public static EmploymentResponseDTO toResponseDTO(Employment employmentPeriod) {
        return new EmploymentResponseDTO(
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
