package com.sa.healntrack.employee_service.payroll.application.port.in;

import java.util.List;

import com.sa.healntrack.employee_service.payroll.application.port.in.command.FindAllPayrollsQuery;
import com.sa.healntrack.employee_service.payroll.domain.Payroll;

public interface FindAllPayrolls {
    List<Payroll> findAllPayrolls(FindAllPayrollsQuery query);
}
