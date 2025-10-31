package com.sa.healntrack.employee_service.employment_period.application.port.in.find_employment_periods;

import java.util.List;

import com.sa.healntrack.employee_service.employment_period.domain.EmploymentPeriod;

public interface FindAllEmploymentPeriods {
    List<EmploymentPeriod> findAllEmploymentPeriods(FindAllEmploymentPeriodsQuery query);
}
