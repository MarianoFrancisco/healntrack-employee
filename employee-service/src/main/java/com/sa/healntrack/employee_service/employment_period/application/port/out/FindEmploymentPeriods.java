package com.sa.healntrack.employee_service.employment_period.application.port.out;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.sa.healntrack.employee_service.employment_period.application.port.in.find_employment_periods.FindAllEmploymentPeriodsQuery;
import com.sa.healntrack.employee_service.employment_period.domain.Employee;
import com.sa.healntrack.employee_service.employment_period.domain.EmploymentPeriod;

public interface FindEmploymentPeriods {
    List<EmploymentPeriod> findAllEmploymentPeriods(FindAllEmploymentPeriodsQuery query);
    boolean existsByEmployeeAndEndDate(Employee employee, LocalDate endDate);
    Optional<EmploymentPeriod> findByEmployeeAndEndDate(Employee employee, LocalDate endDate);
}
