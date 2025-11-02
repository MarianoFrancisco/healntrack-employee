package com.sa.healntrack.employee_service.payroll.application.port.out;

import com.sa.healntrack.employee_service.payroll.domain.Payroll;

public interface StorePayroll {
    Payroll save(Payroll payroll);
}
