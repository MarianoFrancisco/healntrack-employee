package com.sa.healntrack.employee_service.payroll.application.port.out;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.sa.healntrack.employee_service.payroll.application.port.in.command.FindAllPayrollsQuery;
import com.sa.healntrack.employee_service.payroll.domain.Payroll;

public interface FindPayrolls {
    List<Payroll> findPayrolls(FindAllPayrollsQuery query);
    boolean existsByStartDateAndEndDate(LocalDate startDate, LocalDate endDate);
    Optional<Payroll> findOverlapPayroll(LocalDate startDate, LocalDate endDate);
}
