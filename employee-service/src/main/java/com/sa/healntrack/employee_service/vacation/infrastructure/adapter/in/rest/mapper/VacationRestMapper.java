package com.sa.healntrack.employee_service.vacation.infrastructure.adapter.in.rest.mapper;

import com.sa.healntrack.employee_service.vacation.domain.Vacation;
import com.sa.healntrack.employee_service.vacation.infrastructure.adapter.in.rest.dto.VacationResponseDTO;

public class VacationRestMapper {

    private VacationRestMapper() {
        // Private constructor to prevent instantiation
    }

    public static VacationResponseDTO toResponseDTO(Vacation vacation) {
        String reviewedBy = vacation.getApprovedBy() != null
                ? vacation.getApprovedBy().getEmployee().getFullname()
                : null;

        return new VacationResponseDTO(
                vacation.getId().value(),
                vacation.getEmployee().getCui().value(),
                vacation.getEmployee().getFullname(),
                vacation.getEmployee().getDepartment().getCode().value(),
                vacation.getEmployee().getDepartment().getName(),
                vacation.getPeriod().startDate(),
                vacation.getPeriod().endDate(),
                vacation.getRequestedAt(),
                reviewedBy,
                vacation.getApprovedAt(),
                vacation.getStatus()
        );
    }
}
