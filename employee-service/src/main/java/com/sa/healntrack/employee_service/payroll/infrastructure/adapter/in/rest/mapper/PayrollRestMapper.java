package com.sa.healntrack.employee_service.payroll.infrastructure.adapter.in.rest.mapper;

import com.sa.healntrack.employee_service.payroll.domain.Payroll;
import com.sa.healntrack.employee_service.payroll.infrastructure.adapter.in.rest.dto.PayrollResponseDTO;

public class PayrollRestMapper {

    public static PayrollResponseDTO toResponseDTO(Payroll payroll) {
        return new PayrollResponseDTO(
                payroll.getId().value(),
                payroll.getPeriod().startDate(),
                payroll.getPeriod().endDate(),
                payroll.getPayDay(),
                payroll.getType(),
                payroll.getTotalGrossAmount(),
                payroll.getTotalIgssDeduction(),
                payroll.getTotalIrtraDeduction(),
                payroll.getTotalNetAmount()
        );
    }
}

