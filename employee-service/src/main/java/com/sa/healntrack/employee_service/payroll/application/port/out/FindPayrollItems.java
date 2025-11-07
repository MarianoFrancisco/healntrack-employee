package com.sa.healntrack.employee_service.payroll.application.port.out;

import java.util.List;

import com.sa.healntrack.employee_service.payroll.application.port.in.command.FindAllPayrollsQuery;
import com.sa.healntrack.employee_service.payroll.domain.PayrollItem;

public interface FindPayrollItems {
    List<PayrollItem> findAll(FindAllPayrollsQuery query);
}
