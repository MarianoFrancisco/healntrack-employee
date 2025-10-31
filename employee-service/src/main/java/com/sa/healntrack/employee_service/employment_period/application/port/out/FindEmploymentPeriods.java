package com.sa.healntrack.employee_service.employment_period.application.port.out;

import java.time.LocalDate;
import java.util.Optional;

import com.sa.healntrack.employee_service.employment_period.domain.Employee;
import com.sa.healntrack.employee_service.employment_period.domain.EmploymentPeriod;

public interface FindEmploymentPeriods {
    boolean existsByEmployeeAndEndDate(Employee employee, LocalDate endDate);
    Optional<EmploymentPeriod> findByEmployeeAndEndDate(Employee employee, LocalDate endDate);
}
