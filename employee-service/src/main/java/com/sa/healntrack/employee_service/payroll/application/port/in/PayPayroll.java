package com.sa.healntrack.employee_service.payroll.application.port.in;

import com.sa.healntrack.employee_service.payroll.application.port.in.command.PayPayrollCommand;
import com.sa.healntrack.employee_service.payroll.domain.Payroll;

public interface PayPayroll {
    Payroll payPayroll(PayPayrollCommand command);
}
