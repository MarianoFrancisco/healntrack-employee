package com.sa.healntrack.employee_service.payroll.infrastructure.adapter.in.rest.mapper;

import com.sa.healntrack.employee_service.payroll.domain.PayrollItem;
import com.sa.healntrack.employee_service.payroll.infrastructure.adapter.in.rest.dto.PayrollItemResponseDTO;

public class PayrollItemRestMapper {
    private PayrollItemRestMapper() {
    }

    public static PayrollItemResponseDTO toResponseDTO(PayrollItem payrollItem) {
        return new PayrollItemResponseDTO(
                payrollItem.getId().value(),
                payrollItem.getPayroll().getPeriod().startDate(),
                payrollItem.getPayroll().getPeriod().endDate(),
                payrollItem.getPayroll().getPayDay(),
                payrollItem.getEmployee().getCui().value(),
                payrollItem.getEmployee().getFullname(),
                payrollItem.getDepartment().getCode().value(),
                payrollItem.getDepartment().getName(),
                payrollItem.getGrossSalary(),
                payrollItem.getIgssDeduction(),
                payrollItem.getIrtraDeduction(),
                payrollItem.getNetSalary(),
                payrollItem.getNotes()
        );
    }
}
