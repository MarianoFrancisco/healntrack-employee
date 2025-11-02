package com.sa.healntrack.employee_service.payroll.infrastructure.adapter.in.rest.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.sa.healntrack.employee_service.payroll.domain.Payroll;
import com.sa.healntrack.employee_service.payroll.domain.PayrollItem;
import com.sa.healntrack.employee_service.payroll.infrastructure.adapter.in.rest.dto.PayrollResponseDTO;
import com.sa.healntrack.employee_service.payroll.infrastructure.adapter.in.rest.dto.PayrollItemResponseDTO;

public class PayrollRestMapper {

    public static PayrollResponseDTO toResponseDTO(Payroll payroll) {
        List<PayrollItemResponseDTO> items = payroll.getItems().stream()
                .map(PayrollRestMapper::toItemResponseDTO)
                .collect(Collectors.toList());

        return new PayrollResponseDTO(
                payroll.getId().value(),
                payroll.getPeriod().startDate(),
                payroll.getPeriod().endDate(),
                payroll.getPayDay(),
                payroll.getType(),
                payroll.getTotalGrossAmount(),
                payroll.getTotalIgssDeduction(),
                payroll.getTotalIrtraDeduction(),
                payroll.getTotalNetAmount(),
                items
        );
    }

    private static PayrollItemResponseDTO toItemResponseDTO(PayrollItem item) {
        return new PayrollItemResponseDTO(
                item.getId().value(),
                item.getEmployee().getCui().value(),
                item.getEmployee().getFullname(),
                item.getDepartment().getCode().value(),
                item.getDepartment().getName(),
                item.getGrossSalary(),
                item.getIgssDeduction(),
                item.getIrtraDeduction(),
                item.getNetSalary(),
                item.getNotes()
        );
    }
}

