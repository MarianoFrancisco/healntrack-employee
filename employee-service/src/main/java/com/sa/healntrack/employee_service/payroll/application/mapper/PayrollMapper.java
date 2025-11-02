package com.sa.healntrack.employee_service.payroll.application.mapper;

import com.sa.healntrack.employee_service.payroll.application.port.in.command.PayPayrollCommand;
import com.sa.healntrack.employee_service.payroll.domain.Payroll;
import com.sa.healntrack.employee_service.payroll.domain.PayrollId;

public class PayrollMapper {
    private PayrollMapper() {
        // Private constructor to prevent instantiation
    }

    public static Payroll toDomain(PayPayrollCommand command) {
        return new Payroll(
            PayrollId.generate().value(),
            command.startDate(),
            command.endDate(),
            command.payDay(),
            command.type()
        );
    }
}
